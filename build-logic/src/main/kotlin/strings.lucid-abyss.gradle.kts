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
        dependsOn(generateStringsInterface) // Implementations might depend on Interface existing? Code-wise yes, but generation logic is independent.
        // But to ensure consistent order or parallel execution, independent is fine.
        // However, we want to register the output dir once.
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
            // All output to same dir, so one registration is enough.
            // But strict correctness might prefer registering task outputs.
            // Since they output to same dir, Gradle might complain if we register same dir multiple times or different tasks output to same dir without overlap declaration.
            // But they generate DIFFERENT files.
            // Let's just register the dir once linked to the aggregate task or interface task.
        }
    }
}
