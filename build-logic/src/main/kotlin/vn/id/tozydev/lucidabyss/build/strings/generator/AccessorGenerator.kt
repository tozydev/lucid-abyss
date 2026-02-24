package vn.id.tozydev.lucidabyss.build.strings.generator

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import vn.id.tozydev.lucidabyss.build.strings.Node
import vn.id.tozydev.lucidabyss.core.SiteLanguage

internal fun generateAccessorCode(packageName: String, structure: Node.Object): FileSpec {
    val fileSpec = FileSpec.builder(packageName, "StringsAccessor")

    val localizedStringsClass = ClassName(packageName, "LocalizedStrings")
    val siteLanguageClass = SiteLanguage::class.asClassName()

    val objectType = TypeSpec.objectBuilder("Strings")
        .addSuperinterface(localizedStringsClass)
        .addModifiers(KModifier.PUBLIC)

    val mutableStateFlowClass = ClassName("kotlinx.coroutines.flow", "MutableStateFlow")

    // val language = MutableStateFlow(SiteLanguage.Default)
    objectType.addProperty(
        PropertySpec.builder("language", mutableStateFlowClass.parameterizedBy(siteLanguageClass))
            .initializer("%T(%T.Default)", mutableStateFlowClass, siteLanguageClass)
            .build()
    )

    val currentGetter = FunSpec.getterBuilder()
        .beginControlFlow("return when(language.value)")

    SiteLanguage.entries.forEach { lang ->
        currentGetter.addStatement("%T.${lang.name} -> %T", siteLanguageClass, ClassName(packageName, "Strings${lang.name}"))
    }

    currentGetter.endControlFlow()

    objectType.addProperty(
        PropertySpec.builder("current", localizedStringsClass)
            .getter(currentGetter.build())
            .build()
    )

    val typeT = TypeVariableName("T")
    objectType.addFunction(
        FunSpec.builder("withLanguage")
            .addModifiers(KModifier.INLINE)
            .addTypeVariable(typeT)
            .addParameter("lang", siteLanguageClass)
            .addParameter("block", LambdaTypeName.get(returnType = typeT))
            .returns(typeT)
            .addStatement("val old = language.value")
            .addStatement("language.value = lang")
            .beginControlFlow("try")
            .addStatement("return block()")
            .nextControlFlow("finally")
            .addStatement("language.value = old")
            .endControlFlow()
            .build()
    )

    structure.children.forEach { child ->
        addDelegateMember(objectType, child, packageName)
    }

    fileSpec.addType(objectType.build())
    return fileSpec.build()
}

private fun addDelegateMember(typeSpec: TypeSpec.Builder, node: Node, packageName: String) {
    val kotlinName = toCamelCase(node.name)
    when (node) {
        is Node.Object -> addDelegateObjectProperty(typeSpec, kotlinName, node, packageName)
        is Node.SimpleString -> addDelegateStringProperty(typeSpec, kotlinName, node)
        is Node.MultiPartString -> addDelegateMultipartFunction(typeSpec, kotlinName, node)
    }
}

private fun addDelegateObjectProperty(typeSpec: TypeSpec.Builder, name: String, node: Node.Object, packageName: String) {
    val typeName = ClassName(packageName, node.fullPath())
    typeSpec.addProperty(
        PropertySpec.builder(name, typeName)
            .addModifiers(KModifier.OVERRIDE)
            .getter(FunSpec.getterBuilder().addStatement("return current.$name").build())
            .build()
    )
}

private fun addDelegateStringProperty(typeSpec: TypeSpec.Builder, name: String, node: Node.SimpleString) {
    val args = extractArgs(node.values)
    if (args.isEmpty()) {
        typeSpec.addProperty(
            PropertySpec.builder(name, String::class)
                .addModifiers(KModifier.OVERRIDE)
                .getter(FunSpec.getterBuilder().addStatement("return current.$name").build())
                .build()
        )
    } else {
        val funSpec = FunSpec.builder(name)
            .addModifiers(KModifier.OVERRIDE)
            .returns(String::class)

        val callArgs = args.joinToString(", ")
        args.forEach { arg ->
            funSpec.addParameter(arg, String::class)
        }

        funSpec.addStatement("return current.$name($callArgs)")
        typeSpec.addFunction(funSpec.build())
    }
}

private fun addDelegateMultipartFunction(typeSpec: TypeSpec.Builder, name: String, node: Node.MultiPartString) {
    val defaultList = node.values[SiteLanguage.Default] ?: emptyList()
    val size = defaultList.size
    if (size > 0) {
        val typeT = TypeVariableName("T")
        val lambdaParams = (1..size).map { String::class.asTypeName() }
        val lambdaType = LambdaTypeName.get(parameters = lambdaParams.toTypedArray(), returnType = typeT)

        val funSpec = FunSpec.builder(name)
            .addModifiers(KModifier.OVERRIDE)
            .addTypeVariable(typeT)
            .addParameter("render", lambdaType)
            .returns(typeT)
            .addStatement("return current.$name(render)")

        typeSpec.addFunction(funSpec.build())
    }
}
