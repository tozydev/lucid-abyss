package vn.id.tozydev.lucidabyss.build.strings

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.asTypeName

class ComposableArrayStringHook : InterfaceHook {
    override fun visit(
        node: Node.Collection,
        context: InterfaceGenContext,
    ): Boolean {
        val isAllStrings = node.values.values.all { it is Node.StringScalar }
        if (!isAllStrings || node.values.isEmpty()) return false

        val propertyName = toCamelCase(node.key)
        val composableAnnotation = ClassName("androidx.compose.runtime", "Composable")

        val lambdaParamTypes = List(node.values.size) { String::class.asTypeName() }
        val lambdaType =
            LambdaTypeName
                .get(
                    parameters = lambdaParamTypes.map { ParameterSpec.unnamed(it) },
                    returnType = Unit::class.asTypeName(),
                ).copy(annotations = listOf(AnnotationSpec.builder(composableAnnotation).build()))

        val funBuilder =
            FunSpec
                .builder(propertyName)
                .addAnnotation(composableAnnotation)
                .addModifiers(KModifier.PUBLIC, KModifier.INLINE)
                .receiver(context.currentClassName)
                .addParameter("content", lambdaType)

        val args = List(node.values.size) { "$propertyName[$it]" }.joinToString(", ")
        funBuilder.addStatement("content($args)")

        context.fileBuilder.addFunction(funBuilder.build())

        return false
    }
}
