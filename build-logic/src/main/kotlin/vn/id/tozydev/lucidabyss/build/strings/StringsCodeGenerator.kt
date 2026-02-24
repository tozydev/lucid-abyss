package vn.id.tozydev.lucidabyss.build.strings

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import java.util.Locale

internal fun generateInterfaceCode(packageName: String, structure: Node.Object): FileSpec {
    val fileSpec = FileSpec.builder(packageName, "Strings")

    val interfaceType = TypeSpec.interfaceBuilder("LocalizedStrings")
        .addModifiers(KModifier.PUBLIC)

    structure.children.forEach { child ->
        addInterfaceMember(interfaceType, child, packageName)
    }

    fileSpec.addType(interfaceType.build())
    addNestedInterfaces(fileSpec, structure)

    return fileSpec.build()
}

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

internal fun generateAccessorCode(packageName: String, structure: Node.Object): FileSpec {
    val fileSpec = FileSpec.builder(packageName, "StringsAccessor")

    val localizedStringsClass = ClassName(packageName, "LocalizedStrings")
    val siteLanguageClass = SiteLanguage::class.asClassName()

    val objectType = TypeSpec.objectBuilder("Strings")
        .addSuperinterface(localizedStringsClass)
        .addModifiers(KModifier.PUBLIC)

    val mutableStateOf = MemberName("androidx.compose.runtime", "mutableStateOf")
    objectType.addProperty(
        PropertySpec.builder("language", siteLanguageClass)
            .mutable(true)
            .delegate("%M(%T.Default)", mutableStateOf, siteLanguageClass)
            .build()
    )

    val currentGetter = FunSpec.getterBuilder()
        .beginControlFlow("return when(language)")

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
            .addStatement("val old = language")
            .addStatement("language = lang")
            .beginControlFlow("try")
            .addStatement("return block()")
            .nextControlFlow("finally")
            .addStatement("language = old")
            .endControlFlow()
            .build()
    )

    structure.children.forEach { child ->
        addDelegateMember(objectType, child, packageName)
    }

    fileSpec.addType(objectType.build())
    return fileSpec.build()
}

private fun addInterfaceMember(typeSpec: TypeSpec.Builder, node: Node, packageName: String) {
    val kotlinName = toCamelCase(node.name)
    when (node) {
        is Node.Object -> {
            val typeName = ClassName(packageName, node.fullPath())
            typeSpec.addProperty(PropertySpec.builder(kotlinName, typeName).build())
        }
        is Node.SimpleString -> {
            val args = extractArgs(node.values)
            if (args.isEmpty()) {
                typeSpec.addProperty(PropertySpec.builder(kotlinName, String::class).build())
            } else {
                val funSpec = FunSpec.builder(kotlinName)
                    .addModifiers(KModifier.ABSTRACT)
                    .returns(String::class)
                args.forEach { arg ->
                    funSpec.addParameter(arg, String::class)
                }
                typeSpec.addFunction(funSpec.build())
            }
        }
        is Node.MultiPartString -> {
            val defaultList = node.values[SiteLanguage.Default] ?: emptyList()
            val size = defaultList.size
            if (size > 0) {
                val typeT = TypeVariableName("T")
                val lambdaParams = (1..size).map { String::class.asTypeName() }.toTypedArray()
                val lambdaType = LambdaTypeName.get(parameters = lambdaParams, returnType = typeT)

                typeSpec.addFunction(
                    FunSpec.builder(kotlinName)
                        .addModifiers(KModifier.ABSTRACT)
                        .addTypeVariable(typeT)
                        .addParameter("render", lambdaType)
                        .returns(typeT)
                        .build()
                )
            }
        }
    }
}

private fun addNestedInterfaces(fileSpec: FileSpec.Builder, node: Node.Object) {
    val packageName = fileSpec.packageName

    node.children.filterIsInstance<Node.Object>().forEach { child ->
        val interfaceName = child.fullPath()
        val interfaceType = TypeSpec.interfaceBuilder(interfaceName)
            .addModifiers(KModifier.PUBLIC)

        child.children.forEach { grandChild ->
            addInterfaceMember(interfaceType, grandChild, packageName)
        }

        fileSpec.addType(interfaceType.build())
        addNestedInterfaces(fileSpec, child)
    }
}

private fun addImplementationMember(typeSpec: TypeSpec.Builder, node: Node, language: SiteLanguage, packageName: String) {
    val kotlinName = toCamelCase(node.name)
    when (node) {
        is Node.Object -> {
            val typeName = ClassName(packageName, node.fullPath())
            val anonymousClass = TypeSpec.anonymousClassBuilder()
                .addSuperinterface(typeName)

            node.children.forEach { child ->
                addImplementationMember(anonymousClass, child, language, packageName)
            }

            typeSpec.addProperty(
                PropertySpec.builder(kotlinName, typeName)
                    .addModifiers(KModifier.OVERRIDE)
                    .initializer("%L", anonymousClass.build())
                    .build()
            )
        }
        is Node.SimpleString -> {
            val value = (node.values[language] ?: "").escape()
            val args = extractArgs(node.values)
            if (args.isEmpty()) {
                typeSpec.addProperty(
                    PropertySpec.builder(kotlinName, String::class)
                        .addModifiers(KModifier.OVERRIDE)
                        .initializer("%S", value)
                        .build()
                )
            } else {
                val templateName = "${kotlinName}Template"
                typeSpec.addProperty(
                    PropertySpec.builder(templateName, String::class)
                        .addModifiers(KModifier.PRIVATE)
                        .initializer("%S", value)
                        .build()
                )

                val funSpec = FunSpec.builder(kotlinName)
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
        is Node.MultiPartString -> {
             val defaultList = node.values[SiteLanguage.Default] ?: emptyList()
             val size = defaultList.size
             if (size > 0) {
                val list = node.values[language] ?: emptyList()
                val paddedList = list + List(size - list.size) { "" }

                val partsName = "${kotlinName}Parts"
                val format = "arrayOf(" + paddedList.joinToString(", ") { "%S" } + ")"
                val args = paddedList.toTypedArray()

                typeSpec.addProperty(
                    PropertySpec.builder(partsName, Array::class.parameterizedBy(String::class))
                        .addModifiers(KModifier.PRIVATE)
                        .initializer(format, *args)
                        .build()
                )

                val typeT = TypeVariableName("T")
                val lambdaParams = (1..size).map { String::class.asTypeName() }.toTypedArray()
                val lambdaType = LambdaTypeName.get(parameters = lambdaParams, returnType = typeT)

                val funSpec = FunSpec.builder(kotlinName)
                    .addModifiers(KModifier.OVERRIDE)
                    .addTypeVariable(typeT)
                    .addParameter("render", lambdaType)
                    .returns(typeT)

                val callArgs = (0 until size).joinToString(", ") { "$partsName[$it]" }
                funSpec.addStatement("return render($callArgs)")

                typeSpec.addFunction(funSpec.build())
             }
        }
    }
}

private fun addDelegateMember(typeSpec: TypeSpec.Builder, node: Node, packageName: String) {
    val kotlinName = toCamelCase(node.name)
    when (node) {
        is Node.Object -> {
            val typeName = ClassName(packageName, node.fullPath())
            typeSpec.addProperty(
                PropertySpec.builder(kotlinName, typeName)
                    .addModifiers(KModifier.OVERRIDE)
                    .getter(FunSpec.getterBuilder().addStatement("return current.$kotlinName").build())
                    .build()
            )
        }
        is Node.SimpleString -> {
            val args = extractArgs(node.values)
            if (args.isEmpty()) {
                typeSpec.addProperty(
                    PropertySpec.builder(kotlinName, String::class)
                        .addModifiers(KModifier.OVERRIDE)
                        .getter(FunSpec.getterBuilder().addStatement("return current.$kotlinName").build())
                        .build()
                )
            } else {
                val funSpec = FunSpec.builder(kotlinName)
                    .addModifiers(KModifier.OVERRIDE)
                    .returns(String::class)

                val callArgs = args.joinToString(", ")
                args.forEach { arg ->
                    funSpec.addParameter(arg, String::class)
                }

                funSpec.addStatement("return current.$kotlinName($callArgs)")
                typeSpec.addFunction(funSpec.build())
            }
        }
        is Node.MultiPartString -> {
            val defaultList = node.values[SiteLanguage.Default] ?: emptyList()
            val size = defaultList.size
            if (size > 0) {
                val typeT = TypeVariableName("T")
                val lambdaParams = (1..size).map { String::class.asTypeName() }.toTypedArray()
                val lambdaType = LambdaTypeName.get(parameters = lambdaParams, returnType = typeT)

                val funSpec = FunSpec.builder(kotlinName)
                    .addModifiers(KModifier.OVERRIDE)
                    .addTypeVariable(typeT)
                    .addParameter("render", lambdaType)
                    .returns(typeT)
                    .addStatement("return current.$kotlinName(render)")

                typeSpec.addFunction(funSpec.build())
            }
        }
    }
}

private fun Node.Object.fullPath(): String {
    val parentObj = parent as? Node.Object
    val parentPath = if (parentObj != null && parentObj.name != "Strings") parentObj.fullPath() else ""

    val myName = toPascalCase(name)
    return if (parentPath.isNotEmpty()) "${parentPath}$myName" else myName
}

private fun toCamelCase(s: String): String {
    val parts = s.split('-', '_', '.')
    if (parts.isEmpty()) return s
    val first = parts[0].replaceFirstChar { it.lowercase(Locale.getDefault()) }
    val rest = parts.drop(1).joinToString("") { part ->
        part.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
    return first + rest
}

private fun toPascalCase(s: String): String {
     val parts = s.split('-', '_', '.')
     return parts.joinToString("") { part ->
         part.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
     }
}

private fun extractArgs(values: Map<SiteLanguage, String?>): Set<String> {
    val regex = """\$\{([^}]+)\}""".toRegex()
    val allArgs = mutableSetOf<String>()
    values.values.filterNotNull().forEach { str ->
        regex.findAll(str).forEach { match ->
            allArgs.add(match.groupValues[1])
        }
    }
    return allArgs
}

private fun String.escape(): String {
    return this.replace("\"", "\\\"").replace("$", "\\$")
}
