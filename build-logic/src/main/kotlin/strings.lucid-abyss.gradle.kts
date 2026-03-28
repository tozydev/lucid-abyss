import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper
import vn.id.tozydev.lucidabyss.build.strings.GenerateStringsTask

plugins.withType<KotlinMultiplatformPluginWrapper> {
    val stringsProjectDir = layout.projectDirectory.dir("src/jsMain/resources/strings")
    val generatedStringsDir = layout.buildDirectory.dir("generated/strings/src/jsMain/kotlin")
    val generatedPackageName = "vn.id.tozydev.lucidabyss.strings"

    val generateStrings by tasks.registering(GenerateStringsTask::class) {
        stringsDir = stringsProjectDir
        outputDir = generatedStringsDir
        packageName = generatedPackageName
        defaultLanguage = "vi"
        interfaceName = "LocalizedStrings"
        implementationClassPrefix = "Strings"
        accessorName = "Strings"
    }

    configure<KotlinMultiplatformExtension> {
        sourceSets {
            jsMain {
                kotlin.srcDir(generateStrings.map { it.outputDir })
            }
        }
    }
}
