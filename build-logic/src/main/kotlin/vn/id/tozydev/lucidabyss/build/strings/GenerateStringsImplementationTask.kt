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
                val map: Map<String, Any> = yaml.load(file.inputStream()) ?: emptyMap()
                stringsMap[language] = map
            }
        }

        val structure = buildStructure(stringsMap)
        generateImplementationCode(packageName.get(), structure, siteLanguage).writeTo(outputDir.get().asFile)
    }

    private fun buildStructure(stringsMap: Map<SiteLanguage, Map<String, Any>>): Node.Object {
        val root = Node.Object("Strings")
        val allKeys = stringsMap.values.flatMap { it.keys }.toSet()

        allKeys.forEach { key ->
            processKey(root, key, stringsMap)
        }

        return root
    }

    private fun processKey(parent: Node.Object, key: String, data: Map<SiteLanguage, Map<String, Any>>) {
        val values = data.mapValues { it.value[key] }
        val nonNullValues = values.values.filterNotNull()

        if (nonNullValues.isEmpty()) return

        val firstValue = nonNullValues.first()

        when (firstValue) {
            is Map<*, *> -> {
                val node = Node.Object(key)
                node.parent = parent
                parent.children.add(node)
                val childData = data.mapValues {
                    (it.value[key] as? Map<String, Any>) ?: emptyMap()
                }
                val allChildKeys = childData.values.flatMap { it.keys }.toSet()
                allChildKeys.forEach { childKey ->
                    processKey(node, childKey, childData)
                }
            }
            is List<*> -> {
                val node = Node.MultiPartString(key, values as Map<SiteLanguage, List<String>?>)
                node.parent = parent
                parent.children.add(node)
            }
            is String -> {
                val node = Node.SimpleString(key, values as Map<SiteLanguage, String?>)
                node.parent = parent
                parent.children.add(node)
            }
        }
    }
}
