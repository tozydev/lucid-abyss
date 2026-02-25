package vn.id.tozydev.lucidabyss.build.strings

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import org.yaml.snakeyaml.Yaml
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.build.strings.generator.generateInterfaceCode
import vn.id.tozydev.lucidabyss.build.strings.generator.generateAccessorCode
import java.io.File

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
        val stringsMap = mutableMapOf<SiteLanguage, Map<String, Any>>()
        val yaml = Yaml()

        SiteLanguage.entries.forEach { language ->
            val file = stringsDir.file("${language.code}.yaml").get().asFile
            if (file.exists()) {
                // Using unchecked cast here is unavoidable at the boundary,
                // but we suppress it or accept it.
                // Yaml.load returns Object.
                @Suppress("UNCHECKED_CAST")
                val map = (yaml.load(file.inputStream()) as? Map<String, Any>) ?: emptyMap()
                stringsMap[language] = map
            }
        }

        // Use the new builder
        val structure = buildStringNodeTree(stringsMap)

        generateInterfaceCode(packageName.get(), structure).writeTo(outputDir.get().asFile)
        generateAccessorCode(packageName.get(), structure).writeTo(outputDir.get().asFile)
    }
}
