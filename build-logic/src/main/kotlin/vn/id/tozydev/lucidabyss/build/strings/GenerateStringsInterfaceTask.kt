package vn.id.tozydev.lucidabyss.build.strings

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import vn.id.tozydev.lucidabyss.build.strings.generator.generateAccessorCode
import vn.id.tozydev.lucidabyss.build.strings.generator.generateInterfaceCode
import vn.id.tozydev.lucidabyss.core.SiteLanguage

@CacheableTask
abstract class GenerateStringsInterfaceTask : DefaultTask() {
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val stringsDir: DirectoryProperty

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @get:Input
    abstract val packageName: Property<String>

    @TaskAction
    fun generate() {
        val files =
            SiteLanguage.entries.associateWith { language ->
                stringsDir.file("${language.code}.yaml").get().asFile
            }

        val structure = buildStringNodeTree(files)

        generateInterfaceCode(packageName.get(), structure).writeTo(outputDir.get().asFile)
        generateAccessorCode(packageName.get(), structure).writeTo(outputDir.get().asFile)
    }
}
