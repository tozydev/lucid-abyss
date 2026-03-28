@file:OptIn(ExperimentalTime::class)

import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import kotlin.time.ExperimentalTime

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    id("blog.lucid-abyss")
    id("strings.lucid-abyss")
}

kobweb {
    app {
        index {
            head.add {
                link("/lucid-abyss.css", rel = "stylesheet")
                link(rel = "stylesheet", href = "/fonts/faces.css")
                link(
                    rel = "stylesheet",
                    href =
                        "https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200",
                )
            }
        }
        export {
            enableTraces()
        }
    }
    markdown {
        defaultLayout = ".components.layouts.PostLayout"
        handlers {
            useSilk = false
        }
    }
}

kotlin {
    configAsKobwebApplication("lucid-abyss")

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
            implementation(libs.kobweb.silk)
            implementation(libs.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlin.wrappers.browser)

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
}
