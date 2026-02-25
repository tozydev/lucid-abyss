package vn.id.tozydev.lucidabyss.build.strings.generator

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.asTypeName
import vn.id.tozydev.lucidabyss.build.strings.Node
import vn.id.tozydev.lucidabyss.core.SiteLanguage

internal fun generateInterfaceCode(packageName: String, structure: Node.Object): FileSpec {
    val fileSpec = FileSpec.builder(packageName, "LocalizedStrings")

    val interfaceType = TypeSpec.interfaceBuilder("LocalizedStrings")
        .addModifiers(KModifier.PUBLIC)

    structure.children.forEach { child ->
        addInterfaceMember(interfaceType, child, packageName)
    }

    fileSpec.addType(interfaceType.build())
    addNestedInterfaces(fileSpec, structure)

    return fileSpec.build()
}

private fun addInterfaceMember(typeSpec: TypeSpec.Builder, node: Node, packageName: String) {
    val kotlinName = toCamelCase(node.name)
    when (node) {
        is Node.Object -> addInterfaceObjectProperty(typeSpec, kotlinName, node, packageName)
        is Node.SimpleString -> addInterfaceStringProperty(typeSpec, kotlinName, node)
        is Node.MultiPartString -> addInterfaceMultipartFunction(typeSpec, kotlinName, node)
    }
}

private fun addInterfaceObjectProperty(typeSpec: TypeSpec.Builder, name: String, node: Node.Object, packageName: String) {
    val typeName = ClassName(packageName, node.fullPath())
    typeSpec.addProperty(PropertySpec.builder(name, typeName).build())
}

private fun addInterfaceStringProperty(typeSpec: TypeSpec.Builder, name: String, node: Node.SimpleString) {
    val args = extractArgs(node.values)
    if (args.isEmpty()) {
        typeSpec.addProperty(PropertySpec.builder(name, String::class).build())
    } else {
        val funSpec = FunSpec.builder(name)
            .addModifiers(KModifier.ABSTRACT)
            .returns(String::class)
        args.forEach { arg ->
            funSpec.addParameter(arg, String::class)
        }
        typeSpec.addFunction(funSpec.build())
    }
}

private fun addInterfaceMultipartFunction(typeSpec: TypeSpec.Builder, name: String, node: Node.MultiPartString) {
    val defaultList = node.values[SiteLanguage.Default] ?: emptyList()
    val size = defaultList.size
    if (size > 0) {
        val typeT = TypeVariableName("T")
        val lambdaParams = (1..size).map { String::class.asTypeName() }
        val lambdaType = LambdaTypeName.get(parameters = lambdaParams.toTypedArray(), returnType = typeT)

        typeSpec.addFunction(
            FunSpec.builder(name)
                .addModifiers(KModifier.ABSTRACT)
                .addTypeVariable(typeT)
                .addParameter("render", lambdaType)
                .returns(typeT)
                .build()
        )
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
