package vn.id.tozydev.lucidabyss.build.strings.generator

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import vn.id.tozydev.lucidabyss.build.strings.Node
import vn.id.tozydev.lucidabyss.core.SiteLanguage

private const val ROOT_INTERFACE_TYPE_NAME = "LocalizedStrings"

internal fun generateInterfaceCode(
    packageName: String,
    structure: Node.Object,
): FileSpec {
    val fileSpec = FileSpec.builder(packageName, ROOT_INTERFACE_TYPE_NAME)

    val interfaceType =
        TypeSpec
            .interfaceBuilder(ROOT_INTERFACE_TYPE_NAME)
            .addModifiers(KModifier.PUBLIC)

    context(fileSpec, interfaceType, ROOT_INTERFACE_TYPE_NAME) {
        structure.children.forEach { child ->
            addInterfaceMember(child, packageName)
        }

        fileSpec.addType(interfaceType.build())
        addNestedInterfaces(structure)
    }

    return fileSpec.build()
}

context(fileSpec: FileSpec.Builder, typeSpec: TypeSpec.Builder, interfaceName: String)
private fun addInterfaceMember(
    node: Node,
    packageName: String,
) {
    val kotlinName = toCamelCase(node.name)
    when (node) {
        is Node.Object -> addInterfaceObjectProperty(kotlinName, node, packageName)
        is Node.SimpleString -> addInterfaceStringProperty(kotlinName, node)
        is Node.MultiPartString -> addInterfaceMultipartProperty(kotlinName, node)
    }
}

context(typeSpec: TypeSpec.Builder)
private fun addInterfaceObjectProperty(
    name: String,
    node: Node.Object,
    packageName: String,
) {
    val typeName = ClassName(packageName, node.fullPath())
    typeSpec.addProperty(PropertySpec.builder(name, typeName).build())
}

context(typeSpec: TypeSpec.Builder)
private fun addInterfaceStringProperty(
    name: String,
    node: Node.SimpleString,
) {
    val args = extractArgs(node.values)
    if (args.isEmpty()) {
        typeSpec.addProperty(PropertySpec.builder(name, String::class).build())
    } else {
        val funSpec =
            FunSpec
                .builder(name)
                .addModifiers(KModifier.ABSTRACT)
                .returns(String::class)
        args.forEach { arg ->
            funSpec.addParameter(arg, String::class)
        }
        typeSpec.addFunction(funSpec.build())
    }
}

private val composableAnnotation =
    AnnotationSpec
        .builder(ClassName("androidx.compose.runtime", "Composable"))
        .build()
private const val CONTENT_LAMBDA_PARAMETER_NAME = "content"

context(fileSpec: FileSpec.Builder, typeSpec: TypeSpec.Builder, interfaceName: String)
private fun addInterfaceMultipartProperty(
    name: String,
    node: Node.MultiPartString,
) {
    val defaultList = node.values[SiteLanguage.Default] ?: emptyList()
    val size = defaultList.size
    if (size > 0) {
        typeSpec.addProperty(
            PropertySpec
                .builder(name, Array::class.parameterizedBy(String::class))
                .addModifiers(KModifier.ABSTRACT)
                .build(),
        )

        val lambdaParams = (1..size).map { String::class.asTypeName() }
        val lambdaType =
            LambdaTypeName
                .get(parameters = lambdaParams.toTypedArray(), returnType = Unit::class.asTypeName())
                .copy(annotations = listOf(composableAnnotation))

        val funSpec =
            FunSpec
                .builder(name)
                .addModifiers(KModifier.INLINE)
                .addParameter(CONTENT_LAMBDA_PARAMETER_NAME, lambdaType)
                .receiver(ClassName.bestGuess(interfaceName))
                .addAnnotation(composableAnnotation)

        val callArgs = (0 until size).joinToString(", ") { "$name[$it]" }
        funSpec.addStatement("$CONTENT_LAMBDA_PARAMETER_NAME($callArgs)")

        fileSpec.addFunction(funSpec.build())
    }
}

context(fileSpec: FileSpec.Builder)
private fun addNestedInterfaces(node: Node.Object) {
    val packageName = fileSpec.packageName

    node.children.filterIsInstance<Node.Object>().forEach { child ->
        val interfaceName = child.fullPath()
        val interfaceType =
            TypeSpec
                .interfaceBuilder(interfaceName)
                .addModifiers(KModifier.PUBLIC)

        context(interfaceType, interfaceName) {
            child.children.forEach { grandChild ->
                addInterfaceMember(grandChild, packageName)
            }
        }

        fileSpec.addType(interfaceType.build())
        addNestedInterfaces(child)
    }
}
