package vn.id.tozydev.lucidabyss.build.strings

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import org.yaml.snakeyaml.Yaml
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
        val stringsMap = mutableMapOf<SiteLanguage, Map<String, Any>>()
        val yaml = Yaml()
        val langCode = languageCode.get()
        val siteLanguage = SiteLanguage.fromCode(langCode)

        SiteLanguage.entries.forEach { language ->
            val file = stringsDir.file("${language.code}.yaml").get().asFile
            if (file.exists()) {
                @Suppress("UNCHECKED_CAST")
                val map = (yaml.load(file.inputStream()) as? Map<String, Any>) ?: emptyMap()
                stringsMap[language] = map
            }
        }

        // Use the new builder
        val structure = buildStringNodeTree(stringsMap)

        generateImplementationCode(packageName.get(), structure, siteLanguage).writeTo(outputDir.get().asFile)
    }
}
