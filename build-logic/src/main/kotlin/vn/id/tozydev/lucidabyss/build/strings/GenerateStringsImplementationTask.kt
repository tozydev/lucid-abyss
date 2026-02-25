package vn.id.tozydev.lucidabyss.build.strings

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.build.strings.generator.generateImplementationCode
import java.io.File

@CacheableTask
abstract class GenerateStringsImplementationTask : DefaultTask() {

    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val stringsDir: DirectoryProperty

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @get:Input
    abstract val packageName: Property<String>

    @get:Input
    abstract val languageCode: Property<String>

    @TaskAction
    fun generate() {
        val files = SiteLanguage.entries.associateWith { language ->
            stringsDir.file("${language.code}.yaml").get().asFile
        }

        val langCode = languageCode.get()
        val siteLanguage = SiteLanguage.fromCode(langCode)

        val structure = buildStringNodeTree(files)

        generateImplementationCode(packageName.get(), structure, siteLanguage).writeTo(outputDir.get().asFile)
    }
}
