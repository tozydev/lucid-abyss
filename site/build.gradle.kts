@file:OptIn(ExperimentalTime::class)

import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import kotlin.time.ExperimentalTime

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
    id("blog.lucid-abyss")
}

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")
            head.add {
                link("/lucid-abyss.css", rel = "stylesheet")
            }
        }
        export {
            enableTraces()
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

            // todo version catalog?
            implementation(devNpm("daisyui", "5.5.14"))
            implementation(devNpm("tailwindcss", "4.1.18"))
            implementation(devNpm("@tailwindcss/postcss", "4.1.18"))
            implementation(devNpm("@tailwindcss/typography", "0.5.19"))
            implementation(devNpm("postcss", "8.5.6"))
            implementation(devNpm("postcss-loader", "8.2.0"))
            implementation(devNpm("css-loader", "7.1.2"))
            implementation(devNpm("mini-css-extract-plugin", "2.9.4"))
            implementation(devNpm("css-minimizer-webpack-plugin", "7.0.4"))
        }
    }
}

tasks {
    val copyProductionStylesheet by registering(Copy::class) {
        from(layout.buildDirectory.dir("kotlin-webpack/js/productionExecutable")) {
            include("*.css")
            include("*.css.map")
        }
        into(layout.projectDirectory.dir(".kobweb/site"))
    }
    kobwebExport {
        finalizedBy(copyProductionStylesheet)
    }
}
