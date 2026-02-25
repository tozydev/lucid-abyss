import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper
import vn.id.tozydev.lucidabyss.build.strings.GenerateStringsImplementationTask
import vn.id.tozydev.lucidabyss.build.strings.GenerateStringsInterfaceTask
import vn.id.tozydev.lucidabyss.core.SiteLanguage

plugins.withType<KotlinMultiplatformPluginWrapper> {
    val stringsProjectDir = layout.projectDirectory.dir("src/jsMain/resources/strings")
    val generatedStringsDir = layout.buildDirectory.dir("generated/strings/src/jsMain/kotlin")
    val generatedPackageName = "vn.id.tozydev.lucidabyss.strings"

    val generateStringsInterface by tasks.registering(GenerateStringsInterfaceTask::class) {
        stringsDir = stringsProjectDir
        outputDir = generatedStringsDir
        packageName.set(generatedPackageName)
    }

    val languageTasks =
        SiteLanguage.entries.map { language ->
            tasks.register<GenerateStringsImplementationTask>("generateStrings${language.name}") {
                stringsDir = stringsProjectDir
                outputDir = generatedStringsDir
                packageName = generatedPackageName
                languageCode = language.code
            }
        }

    @Suppress("unused")
    val generateStrings by tasks.registering {
        dependsOn(generateStringsInterface)
        dependsOn(languageTasks)
    }

    configure<KotlinMultiplatformExtension> {
        sourceSets {
            jsMain {
                kotlin.srcDir(generateStringsInterface.map { it.outputDir })
                languageTasks.forEach { task ->
                    kotlin.srcDir(task.map { it.outputDir })
                }
            }
        }
    }
}
