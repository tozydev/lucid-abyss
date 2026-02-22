import vn.id.tozydev.lucidabyss.build.strings.GenerateStringsTask
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

val generateStrings = tasks.register<GenerateStringsTask>("generateStrings") {
    stringsDir.set(layout.projectDirectory.dir("src/jsMain/resources/strings"))
    outputDir.set(layout.buildDirectory.dir("generated/strings/src/jsMain/kotlin"))
    packageName.set("vn.id.tozydev.lucidabyss.strings")
}

configure<KotlinMultiplatformExtension> {
    sourceSets.configureEach {
        if (name == "jsMain") {
            kotlin.srcDir(generateStrings.map { it.outputDir })
        }
    }
}
