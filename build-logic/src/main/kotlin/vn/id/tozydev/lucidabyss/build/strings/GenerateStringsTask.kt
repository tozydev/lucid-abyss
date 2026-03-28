package vn.id.tozydev.lucidabyss.build.strings

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.work.ChangeType
import org.gradle.work.Incremental
import org.gradle.work.InputChanges
import org.yaml.snakeyaml.Yaml
import java.io.File

@CacheableTask
abstract class GenerateStringsTask : DefaultTask() {
    @get:Incremental
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val stringsDir: DirectoryProperty

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @get:Input
    abstract val packageName: Property<String>

    @get:Input
    @get:Optional
    abstract val interfaceName: Property<String>

    @get:Input
    @get:Optional
    abstract val accessorName: Property<String>

    @get:Input
    @get:Optional
    abstract val implementationClassPrefix: Property<String>

    @get:Input
    abstract val defaultLanguage: Property<String>

    @TaskAction
    fun generate(inputChanges: InputChanges) {
        val stringsFolder = stringsDir.get().asFile
        val outputFolder = outputDir.get().asFile

        if (!inputChanges.isIncremental) {
            outputFolder.deleteRecursively()
        }

        val iName = interfaceName.get()
        val aName = accessorName.get()
        val implPrefix = implementationClassPrefix.get()
        val pkg = packageName.get()

        val files = stringsFolder.listFiles { file -> file.extension == "yaml" } ?: emptyArray()
        val languageFiles = files.associateBy { it.nameWithoutExtension }

        if (languageFiles.isEmpty()) {
            logger.warn("No .yaml files found in ${stringsFolder.absolutePath}")
            return
        }

        val yaml = Yaml()
        val yamlNodes =
            languageFiles.mapValues { (_, file) ->
                file.reader().use { yaml.compose(it) }
            }

        val structure = buildNodeTreeFromYaml(yamlNodes)

        // Always generate shared files because they depend on the union of all inputs
        generateInterface(pkg, iName, structure).writeTo(outputFolder)
        generateAccessor(pkg, iName, aName, structure).writeTo(outputFolder)

        // Process implementations based on changes
        inputChanges.getFileChanges(stringsDir).forEach { change ->
            if (change.file.isDirectory) return@forEach

            val langCode = change.file.nameWithoutExtension
            val implName = "${implPrefix}${langCode.replaceFirstChar { it.uppercase() }}"

            when (change.changeType) {
                ChangeType.REMOVED -> {
                    val packagePath = pkg.replace('.', File.separatorChar)
                    val targetFile = File(outputFolder, "$packagePath${File.separatorChar}$implName.kt")
                    if (targetFile.exists()) {
                        targetFile.delete()
                    }
                }

                ChangeType.ADDED, ChangeType.MODIFIED -> {
                    generateImplementation(pkg, iName, implName, langCode, structure).writeTo(outputFolder)
                }
            }
        }
    }

    private fun generateInterface(
        pkg: String,
        iName: String,
        structure: Node.Object,
    ): FileSpec {
        val fileBuilder = FileSpec.builder(pkg, iName)
        val interfaceClassName = ClassName(pkg, iName)
        val interfaceBuilder = TypeSpec.interfaceBuilder(iName).addModifiers(KModifier.PUBLIC)
        val context = InterfaceGenContext(interfaceBuilder, interfaceClassName, fileBuilder)
        val visitor = InterfaceGeneratorVisitor(listOf(StringTemplateHook(), ComposableArrayStringHook()))

        structure.children.forEach { it.accept(visitor, context) }

        fileBuilder.addType(interfaceBuilder.build())
        return fileBuilder.build()
    }

    private fun generateImplementation(
        pkg: String,
        interfaceName: String,
        implName: String,
        langCode: String,
        structure: Node.Object,
    ): FileSpec {
        val fileBuilder = FileSpec.builder(pkg, implName)
        val interfaceClassName = ClassName(pkg, interfaceName)
        val objectBuilder =
            TypeSpec
                .objectBuilder(implName)
                .addSuperinterface(interfaceClassName)
                .addModifiers(KModifier.PUBLIC)

        val context = ObjectGenContext(objectBuilder, langCode, interfaceClassName)
        val visitor = ObjectGeneratorVisitor(listOf(StringTemplateHook()))

        structure.children.forEach { it.accept(visitor, context) }

        fileBuilder.addType(objectBuilder.build())
        return fileBuilder.build()
    }

    private fun generateAccessor(
        pkg: String,
        interfaceName: String,
        accessorName: String,
        structure: Node.Object,
    ): FileSpec {
        val fileBuilder = FileSpec.builder(pkg, accessorName)
        val interfaceClassName = ClassName(pkg, interfaceName)
        val objectBuilder =
            TypeSpec
                .objectBuilder(accessorName)
                .addSuperinterface(interfaceClassName)
                .addModifiers(KModifier.PUBLIC)

        val currentField =
            PropertySpec
                .builder("_current", interfaceClassName.copy(nullable = true))
                .addModifiers(KModifier.PRIVATE)
                .mutable(true)
                .initializer("null")
                .build()

        val currentProperty =
            PropertySpec
                .builder("current", interfaceClassName)
                .mutable(true)
                .getter(
                    FunSpec
                        .getterBuilder()
                        .addStatement("return _current ?: error(%S)", "$accessorName is not initialized")
                        .build(),
                ).setter(
                    FunSpec
                        .setterBuilder()
                        .addParameter("value", interfaceClassName)
                        .addStatement("_current = value")
                        .build(),
                ).build()

        objectBuilder.addProperty(currentField)
        objectBuilder.addProperty(currentProperty)

        val context = AccessorGenContext(objectBuilder, interfaceClassName)
        val visitor = AccessorGeneratorVisitor(listOf(StringTemplateHook()))

        structure.children.forEach { it.accept(visitor, context) }

        fileBuilder.addType(objectBuilder.build())
        return fileBuilder.build()
    }
}
