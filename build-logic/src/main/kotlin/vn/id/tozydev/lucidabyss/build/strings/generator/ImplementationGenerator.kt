package vn.id.tozydev.lucidabyss.build.strings.generator

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import vn.id.tozydev.lucidabyss.build.strings.Node
import vn.id.tozydev.lucidabyss.core.SiteLanguage

internal fun generateImplementationCode(
    packageName: String,
    structure: Node.Object,
    language: SiteLanguage,
): FileSpec {
    val objectName = "Strings${language.name}"
    val fileSpec = FileSpec.builder(packageName, objectName)

    val objectType =
        TypeSpec
            .objectBuilder(objectName)
            .addSuperinterface(ClassName(packageName, "LocalizedStrings"))
            .addModifiers(KModifier.PUBLIC)

    with(objectType) {
        structure.children.forEach { child ->
            addImplementationMember(child, language, packageName)
        }
    }

    fileSpec.addType(objectType.build())
    return fileSpec.build()
}

context(typeSpec: TypeSpec.Builder)
private fun addImplementationMember(
    node: Node,
    language: SiteLanguage,
    packageName: String,
) {
    val kotlinName = toCamelCase(node.name)
    when (node) {
        is Node.Object -> addImplementationObjectProperty(kotlinName, node, language, packageName)
        is Node.SimpleString -> addImplementationStringProperty(kotlinName, node, language)
        is Node.MultiPartString -> addImplementationMultipartProperty(kotlinName, node, language)
    }
}

context(typeSpec: TypeSpec.Builder)
private fun addImplementationObjectProperty(
    name: String,
    node: Node.Object,
    language: SiteLanguage,
    packageName: String,
) {
    val typeName = ClassName(packageName, node.fullPath())
    val anonymousClass =
        TypeSpec
            .anonymousClassBuilder()
            .addSuperinterface(typeName)

    with(anonymousClass) {
        node.children.forEach { child ->
            addImplementationMember(child, language, packageName)
        }
    }

    typeSpec.addProperty(
        PropertySpec
            .builder(name, typeName)
            .addModifiers(KModifier.OVERRIDE)
            .initializer("%L", anonymousClass.build())
            .build(),
    )
}

context(typeSpec: TypeSpec.Builder)
private fun addImplementationStringProperty(
    name: String,
    node: Node.SimpleString,
    language: SiteLanguage,
) {
    val value = (node.values[language] ?: "").escape()
    val args = extractArgs(node.values)

    if (args.isEmpty()) {
        typeSpec.addProperty(
            PropertySpec
                .builder(name, String::class)
                .addModifiers(KModifier.OVERRIDE)
                .initializer("%S", value)
                .build(),
        )
    } else {
        val templateName = "${name}Template"
        typeSpec.addProperty(
            PropertySpec
                .builder(templateName, String::class)
                .addModifiers(KModifier.PRIVATE)
                .initializer("%S", value)
                .build(),
        )

        val funSpec =
            FunSpec
                .builder(name)
                .addModifiers(KModifier.OVERRIDE)
                .returns(String::class)

        args.forEach { arg ->
            funSpec.addParameter(arg, String::class)
        }

        funSpec.addStatement("var result = %N", templateName)
        args.forEach { arg ->
            funSpec.addStatement("result = result.replace(\"\\$\" + \"{$arg}\", $arg)")
        }
        funSpec.addStatement("return result")
        typeSpec.addFunction(funSpec.build())
    }
}

context(typeSpec: TypeSpec.Builder)
private fun addImplementationMultipartProperty(
    name: String,
    node: Node.MultiPartString,
    language: SiteLanguage,
) {
    val defaultList = node.values[SiteLanguage.Default] ?: emptyList()
    val size = defaultList.size
    if (size > 0) {
        val list = node.values[language] ?: emptyList()
        val paddedList = list + List(size - list.size) { "" }

        val format = "arrayOf(" + paddedList.joinToString(", ") { "%S" } + ")"
        val args = paddedList.toTypedArray()

        typeSpec.addProperty(
            PropertySpec
                .builder(name, Array::class.parameterizedBy(String::class))
                .addModifiers(KModifier.OVERRIDE)
                .initializer(format, *args)
                .build(),
        )
    }
}
