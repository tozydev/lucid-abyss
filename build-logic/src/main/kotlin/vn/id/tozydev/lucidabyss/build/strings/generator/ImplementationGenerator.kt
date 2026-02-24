package vn.id.tozydev.lucidabyss.build.strings.generator

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import vn.id.tozydev.lucidabyss.build.strings.Node
import vn.id.tozydev.lucidabyss.core.SiteLanguage

internal fun generateImplementationCode(packageName: String, structure: Node.Object, language: SiteLanguage): FileSpec {
    val objectName = "Strings${language.name}"
    val fileSpec = FileSpec.builder(packageName, objectName)

    val objectType = TypeSpec.objectBuilder(objectName)
        .addSuperinterface(ClassName(packageName, "LocalizedStrings"))
        .addModifiers(KModifier.PUBLIC)

    structure.children.forEach { child ->
        addImplementationMember(objectType, child, language, packageName)
    }

    fileSpec.addType(objectType.build())
    return fileSpec.build()
}

private fun addImplementationMember(typeSpec: TypeSpec.Builder, node: Node, language: SiteLanguage, packageName: String) {
    val kotlinName = toCamelCase(node.name)
    when (node) {
        is Node.Object -> addImplementationObjectProperty(typeSpec, kotlinName, node, language, packageName)
        is Node.SimpleString -> addImplementationStringProperty(typeSpec, kotlinName, node, language)
        is Node.MultiPartString -> addImplementationMultipartFunction(typeSpec, kotlinName, node, language)
    }
}

private fun addImplementationObjectProperty(typeSpec: TypeSpec.Builder, name: String, node: Node.Object, language: SiteLanguage, packageName: String) {
    val typeName = ClassName(packageName, node.fullPath())
    val anonymousClass = TypeSpec.anonymousClassBuilder()
        .addSuperinterface(typeName)

    node.children.forEach { child ->
        addImplementationMember(anonymousClass, child, language, packageName)
    }

    typeSpec.addProperty(
        PropertySpec.builder(name, typeName)
            .addModifiers(KModifier.OVERRIDE)
            .initializer("%L", anonymousClass.build())
            .build()
    )
}

private fun addImplementationStringProperty(typeSpec: TypeSpec.Builder, name: String, node: Node.SimpleString, language: SiteLanguage) {
    val value = (node.values[language] ?: "").escape()
    val args = extractArgs(node.values)

    if (args.isEmpty()) {
        typeSpec.addProperty(
            PropertySpec.builder(name, String::class)
                .addModifiers(KModifier.OVERRIDE)
                .initializer("%S", value)
                .build()
        )
    } else {
        val templateName = "${name}Template"
        typeSpec.addProperty(
            PropertySpec.builder(templateName, String::class)
                .addModifiers(KModifier.PRIVATE)
                .initializer("%S", value)
                .build()
        )

        val funSpec = FunSpec.builder(name)
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

private fun addImplementationMultipartFunction(typeSpec: TypeSpec.Builder, name: String, node: Node.MultiPartString, language: SiteLanguage) {
    val defaultList = node.values[SiteLanguage.Default] ?: emptyList()
    val size = defaultList.size
    if (size > 0) {
        val list = node.values[language] ?: emptyList()
        val paddedList = list + List(size - list.size) { "" }

        val partsName = "${name}Parts"
        val format = "arrayOf(" + paddedList.joinToString(", ") { "%S" } + ")"
        val args = paddedList.toTypedArray()

        typeSpec.addProperty(
            PropertySpec.builder(partsName, Array::class.parameterizedBy(String::class))
                .addModifiers(KModifier.PRIVATE)
                .initializer(format, *args)
                .build()
        )

        val typeT = TypeVariableName("T")
        val lambdaParams = (1..size).map { String::class.asTypeName() }
        val lambdaType = LambdaTypeName.get(parameters = lambdaParams.toTypedArray(), returnType = typeT)

        val funSpec = FunSpec.builder(name)
            .addModifiers(KModifier.OVERRIDE)
            .addTypeVariable(typeT)
            .addParameter("render", lambdaType)
            .returns(typeT)

        val callArgs = (0 until size).joinToString(", ") { "$partsName[$it]" }
        funSpec.addStatement("return render($callArgs)")

        typeSpec.addFunction(funSpec.build())
    }
}
