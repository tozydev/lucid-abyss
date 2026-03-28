package vn.id.tozydev.lucidabyss.build.strings

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.asTypeName

class AccessorGeneratorVisitor(
    private val hooks: List<AccessorHook> = emptyList(),
) : NodeVisitor<AccessorGenContext, Unit> {
    override fun visit(
        node: Node.Object,
        context: AccessorGenContext,
    ) {
        if (hooks.any { it.visit(node, context) }) return

        val propertyName = toCamelCase(node.key)
        val interfaceName = toPascalCase(node.key)
        val typeName = context.interfaceClassName.nestedClass(interfaceName)

        context.builder.addProperty(
            PropertySpec
                .builder(propertyName, typeName)
                .addModifiers(KModifier.OVERRIDE)
                .getter(FunSpec.getterBuilder().addStatement("return current.$propertyName").build())
                .build(),
        )
    }

    override fun visit(
        node: Node.Collection,
        context: AccessorGenContext,
    ) {
        if (hooks.any { it.visit(node, context) }) return

        val propertyName = toCamelCase(node.key)
        val firstItem = node.values.values.firstOrNull()

        val itemType =
            if (firstItem is Node.Object) {
                val interfaceName = toPascalCase(node.key)
                context.interfaceClassName.nestedClass(interfaceName)
            } else {
                String::class.asTypeName()
            }
        val listType = List::class.asTypeName().parameterizedBy(itemType)

        context.builder.addProperty(
            PropertySpec
                .builder(propertyName, listType)
                .addModifiers(KModifier.OVERRIDE)
                .getter(FunSpec.getterBuilder().addStatement("return current.$propertyName").build())
                .build(),
        )
    }

    override fun visit(
        node: Node.StringScalar,
        context: AccessorGenContext,
    ) {
        if (hooks.any { it.visit(node, context) }) return

        val propertyName = toCamelCase(node.key)

        context.builder.addProperty(
            PropertySpec
                .builder(propertyName, String::class)
                .addModifiers(KModifier.OVERRIDE)
                .getter(FunSpec.getterBuilder().addStatement("return current.$propertyName").build())
                .build(),
        )
    }
}
