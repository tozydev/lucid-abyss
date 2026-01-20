@file:OptIn(ExperimentalTime::class)

import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import io.github.skeptick.libres.plugin.ResourcesPlugin
import kotlinx.html.link
import kotlin.time.ExperimentalTime

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
    alias(libs.plugins.libres)
    id("blog.lucid-abyss")
    id("site-export.lucid-abyss")
}

kobweb {
    app {
        index {
            head.add {
                link("/lucid-abyss.css", rel = "stylesheet")
            }
        }
    }
    markdown {
        handlers {
            useSilk = false
        }
    }
}

kotlin {
    configAsKobwebApplication("lucid-abyss")

    compilerOptions {
        optIn.addAll(
            "kotlin.time.ExperimentalTime",
        )
        freeCompilerArgs.addAll(
            "-Xcontext-parameters",
        )
    }

    sourceSets {
        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation(libs.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
            implementation(libs.kotlinx.datetime)

            implementation("vn.id.tozydev.lucidabyss:lucid-abyss-core")

            implementation(npm(npm.daisyui))
            implementation(npm(npm.tailwindcss.asProvider()))
            implementation(npm(npm.tailwindcss.typography))
            implementation(npm(npm.tailwindcss.postcss))
            implementation(npm(npm.postcss))
            implementation(npm(npm.webpack.loader.css))
            implementation(npm(npm.webpack.loader.postcss))
            implementation(npm(npm.webpack.plugin.miniCssExtract))
            implementation(npm(npm.webpack.plugin.cssMinimizer))
        }
    }
}

tasks {
    val copyProductionStylesheets by registering(Copy::class) {
        from(layout.buildDirectory.dir("kotlin-webpack/js/productionExecutable")) {
            include("*.css")
            include("*.css.map")
        }
        into(layout.projectDirectory.dir(".kobweb/site"))
    }
    kobwebExport {
        finalizedBy(copyProductionStylesheets)
    }

    val copyDevStylesheets by registering(Copy::class) {
        from(layout.buildDirectory.dir("kotlin-webpack/js/developmentExecutable")) {
            include("*.css")
        }
        into(layout.buildDirectory.dir("processedResources/js/main/public"))
    }
    named("jsBrowserDevelopmentWebpack") {
        finalizedBy(copyDevStylesheets)
    }
    named("kobwebStart") {
        mustRunAfter(copyDevStylesheets)
    }
    afterEvaluate {
        named("kspKotlinJs") {
            mustRunAfter(ResourcesPlugin.GENERATE_RESOURCES_TASK_NAME)
        }
    }
}

libres {
    baseLocaleLanguageCode = "vi"
}
