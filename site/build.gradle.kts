import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.LinkAs
import kotlinx.html.link
import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.TextNode
import vn.id.tozydev.lucidabyss.build.site.TransformSiteHtmlTask

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kfc.latestTools)
    id("blog.lucid-abyss")
    id("strings.lucid-abyss")
    id("pagefind.lucid-abyss")
}

kobweb {
    app {
        index {
            lang = "vi"
            head.add {
                link(rel = "stylesheet", href = "/_la/css/lucid-abyss.css")
                listOf("BeVietnamPro-Regular.woff2", "Nunito.woff2").forEach { fontFileName ->
                    link(
                        rel = "preload",
                        href = "/_la/fonts/$fontFileName",
                        htmlAs = LinkAs.font,
                        type = "font/woff2",
                    ) {
                        attributes["crossorigin"] = "anonymous"
                    }
                }
            }
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

            html = { htmlBlock ->
                val kobwebDom = "com.varabyte.kobweb.compose.dom"
                val w3cDom = "org.w3c.dom"
                val jbDom = "org.jetbrains.compose.web.dom"

                // Convert a set of HTML attributes to an `AttrBuilderContext` lambda block.
                fun org.jsoup.nodes.Attributes.toAttrsBlock(): String {
                    val styleMap = this.takeUnless { it.isEmpty } ?: return "{}"
                    return buildString {
                        append("{")
                        append(
                            styleMap.joinToString(";") { (key, value) ->
                                "attr(\"$key\", \"${value.escapeSingleQuotedText()}\")"
                            },
                        )
                        append("}")
                    }
                }

                fun renderNode(
                    el: Element,
                    indentCount: Int,
                    sb: StringBuilder,
                ) {
                    sb.append("${indent(indentCount)}$kobwebDom.GenericTag<$w3cDom.Element>(\"${el.tagName()}\"")

                    if (el.attributesSize() > 0) {
                        val attrs = el.attributes().toAttrsBlock()
                        sb.append(", attrs = $attrs")
                    }

                    if (el.childNodeSize() > 0) {
                        sb.appendLine(") {")
                        el.childNodes().forEach { child ->
                            if (child is TextNode) {
                                val wholeText = child.wholeText
                                if (wholeText.isNotEmpty()) {
                                    sb.append("${indent(indentCount + 1)}$jbDom.Text(")
                                    if (wholeText == "\n") {
                                        sb.append("\"\\n\"")
                                    } else {
                                        sb.append(
                                            "\"\"\"${
                                                wholeText.removePrefix("\n").escapeTripleQuotedText()
                                            }\"\"\"",
                                        )
                                    }
                                    sb.appendLine(")")
                                }
                            } else if (child is Element) {
                                renderNode(child, indentCount + 1, sb)
                                if (!sb.endsWith("\n")) {
                                    sb.appendLine()
                                }
                            }
                        }
                        sb.appendLine(indent(indentCount) + "}")
                    } else {
                        sb.append(')')
                    }
                }

                val sb = StringBuilder()
                val doc = Jsoup.parseBodyFragment(htmlBlock.literal)
                doc.body().children().forEach { root -> renderNode(root, indentCount = 0, sb) }

                sb.toString()
            }
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
            include("**/*.woff2")
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

            listOf("BeVietnamPro-Regular.woff2", "Nunito.woff2").forEach { fontFileName ->
                val fontBaseName = fontFileName.removeSuffix(".woff2")
                val hashedFontFileName =
                    sourceDir
                        .dir("_la/fonts")
                        .map {
                            it.asFileTree
                                .matching {
                                    include("$fontBaseName.**.woff2")
                                }.singleFile.name
                        }.get()

                doc
                    .select(
                        "link[href=\"/_la/fonts/$fontFileName\"][rel=\"preload\"][as=\"font\"][type=\"font/woff2\"]",
                    ).attr("href", "/_la/fonts/$hashedFontFileName")
            }

            val jsFilename =
                sourceDir
                    .dir("_la/js")
                    .map {
                        it.asFileTree
                            .matching {
                                include("lucid-abyss.**.js")
                                exclude("lucid-abyss.**.js.map")
                            }.singleFile.name
                    }.get()
            doc
                .select("script[src=\"/lucid-abyss.js\"]")
                .attr("src", "/_la/js/$jsFilename")
        }
    }

    pagefindIndex {
        mustRunAfter(transformSiteHtml)
    }

    val cleanupDist by registering(Delete::class) {
        mustRunAfter(copyProductionWebpackAssets, transformSiteHtml, pagefindIndex)

        val distDir = layout.projectDirectory.dir(".kobweb/site")
        delete(distDir.file("lucid-abyss.js"), distDir.file("lucid-abyss.js.map"))

        val distJsDir = distDir.dir("_la/js")
        delete(distJsDir.file("lucid-abyss.js"), distJsDir.file("lucid-abyss.js.map"))

        val pagefindDir = distDir.dir("_la/pagefind")
        delete(
            pagefindDir.file("pagefind-component-ui.css"),
            pagefindDir.file("pagefind-component-ui.js"),
            pagefindDir.file("pagefind-modular-ui.css"),
            pagefindDir.file("pagefind-modular-ui.js"),
            pagefindDir.file("pagefind-ui.css"),
            pagefindDir.file("pagefind-ui.js"),
        )
    }

    kobwebExport {
        finalizedBy(copyProductionWebpackAssets, transformSiteHtml, pagefindIndex, cleanupDist)
    }

    named("jsBrowserProductionGenerateSwcConfig") {
        mustRunAfter("jsBrowserDevelopmentTranspileWithSwc")
    }
}
