import vn.id.tozydev.lucidabyss.build.strings.GenerateStringsInterfaceTask
import vn.id.tozydev.lucidabyss.build.strings.GenerateStringsImplementationTask
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import vn.id.tozydev.lucidabyss.core.SiteLanguage

plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

val generateStringsInterface = tasks.register<GenerateStringsInterfaceTask>("generateStringsInterface") {
    stringsDir.set(layout.projectDirectory.dir("src/jsMain/resources/strings"))
    outputDir.set(layout.buildDirectory.dir("generated/strings/src/jsMain/kotlin"))
    packageName.set("vn.id.tozydev.lucidabyss.strings")
}

// Register tasks for each language
val languageTasks = SiteLanguage.entries.map { language ->
    tasks.register<GenerateStringsImplementationTask>("generateStrings${language.name}") {
        stringsDir.set(layout.projectDirectory.dir("src/jsMain/resources/strings"))
        outputDir.set(layout.buildDirectory.dir("generated/strings/src/jsMain/kotlin"))
        packageName.set("vn.id.tozydev.lucidabyss.strings")
        languageCode.set(language.code)
    }
}

val generateStrings = tasks.register("generateStrings") {
    dependsOn(generateStringsInterface)
    dependsOn(languageTasks)
}

configure<KotlinMultiplatformExtension> {
    sourceSets.configureEach {
        if (name == "jsMain") {
            kotlin.srcDir(generateStringsInterface.map { it.outputDir })
            languageTasks.forEach { task ->
                kotlin.srcDir(task.map { it.outputDir })
            }
        }
    }
}
