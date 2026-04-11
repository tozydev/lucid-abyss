import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile
import vn.id.tozydev.lucidabyss.build.site.TransformSiteHtmlTask

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    id("blog.lucid-abyss")
    id("strings.lucid-abyss")
    id("pagefind.lucid-abyss")
}

kobweb {
    app {
        index {
            lang = "vi"
            head.add {
                link(rel = "stylesheet", href = "/fonts/faces.css")
                link(rel = "stylesheet", href = "/_la/css/lucid-abyss.css")
            }
            scriptAttributes.put("src", "/_la/js/lucid-abyss.js")
            scriptAttributes.put("type", "module")
        }
        export {
            enableTraces()
        }
    }
    markdown {
        defaultLayout = ".components.layouts.PostLayout"
        kobweb.markdown.addSource(
            project.layout.projectDirectory.dir("src/jsMain/resources/sections"),
            ".components.sections",
        )
        handlers {
            useSilk = false

            idGenerator = { heading -> slugify(heading) }

            val widgetPath = "vn.id.tozydev.lucidabyss.components.widgets"

            @Suppress("RegExpUnnecessaryNonCapturingGroup")
            val codeInfoRegex = Regex("^(?:(?<lang>[a-z0-9-]+))?(?:\\s?title=\"(?<title>[^\"]+)\")?\$")

            code = { code ->
                val infoMatchGroups = code.info?.let { codeInfoRegex.matchEntire(it) }?.groups
                val lang = infoMatchGroups?.get("lang")?.value
                val title = infoMatchGroups?.get("title")?.value

                buildString {
                    appendLine("$widgetPath.code.CodeBlock(")
                    appendLine("${indent(1)}code =")
                    appendLine("${indent(2)}\"\"\"${code.literal.escapeTripleQuotedText()}\"\"\",")
                    if (lang != null) {
                        appendLine("${indent(1)}lang = \"$lang\",")
                    }

                    appendLine("${indent(1)}header = { code, lang -> ")
                    if (title != null) {
                        appendLine("${indent(2)}$widgetPath.code.CodeBlockTitle(title = \"$title\")")
                    }
                    appendLine("${indent(2)}$widgetPath.code.CopyButton(code = code)")
                    appendLine("${indent(1)}}")
                    append("${indent(0)})")
                }
            }

            table = { "$widgetPath.Table" }
        }
    }
}

dependencies {
    kobwebServerPlugin(projects.devServerPlugin)
}

kotlin {
    configAsKobwebApplication("lucid-abyss")

    js {
        useEsModules()
    }

    compilerOptions {
        freeCompilerArgs.addAll(
            "-Xcontext-parameters",
        )
    }

    sourceSets {
        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.compose)
            implementation(libs.kobwebx.markdown)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlin.wrappers.browser)

            implementation(npm(npm.tailwindcss.asProvider()))
            implementation(npm(npm.tailwindcss.typography))
            implementation(npm(npm.tailwindcss.webpack))
            implementation(npm(npm.webpack.loader.css))
            implementation(npm(npm.webpack.plugin.miniCssExtract))
            implementation(npm(npm.pagefind))

            implementation(npm(npm.shiki))
        }
    }
}

tasks {
    withType<KotlinJsCompile>().configureEach {
        compilerOptions {
            target = "es2015"
        }
    }

    val copyProductionWebpackAssets by registering(Copy::class) {
        from(layout.buildDirectory.dir("kotlin-webpack/js/productionExecutable/_la")) {
            include("**/*.js", "**/*.js.map")
            include("**/*.css", "**/*.css.map")
        }
        into(layout.projectDirectory.dir(".kobweb/site/_la"))
    }

    val transformSiteHtml by registering(TransformSiteHtmlTask::class) {
        mustRunAfter(copyProductionWebpackAssets)
        sourceDir = layout.projectDirectory.dir(".kobweb/site")
        modifier.set { doc ->
            val cssFileName =
                sourceDir
                    .dir("_la/css")
                    .map {
                        it.asFileTree
                            .matching {
                                include("lucid-abyss.**.css")
                                exclude("lucid-abyss.**.css.map")
                            }.singleFile.name
                    }.get()
            doc
                .select("link[href=\"/_la/css/lucid-abyss.css\"]")
                .attr("href", "/_la/css/$cssFileName")
        }
    }

    pagefindIndex {
        mustRunAfter(transformSiteHtml)
    }

    val cleanupDist by registering(Delete::class) {
        mustRunAfter(copyProductionWebpackAssets, transformSiteHtml, pagefindIndex)
        val distDir = layout.projectDirectory.dir(".kobweb/site")
        delete(distDir.file("lucid-abyss.js"), distDir.file("lucid-abyss.js.map"))
    }

    kobwebExport {
        finalizedBy(copyProductionWebpackAssets, transformSiteHtml, pagefindIndex, cleanupDist)
    }

    named("jsBrowserProductionGenerateSwcConfig") {
        mustRunAfter("jsBrowserDevelopmentTranspileWithSwc")
    }
}
