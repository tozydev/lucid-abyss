package vn.id.tozydev.lucidabyss.build.strings

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName

class InterfaceGeneratorVisitor(
    private val hooks: List<InterfaceHook> = emptyList(),
) : NodeVisitor<InterfaceGenContext, Unit> {
    override fun visit(
        node: Node.Object,
        context: InterfaceGenContext,
    ) {
        if (hooks.any { it.visit(node, context) }) return

        val propertyName = toCamelCase(node.key)
        val interfaceName = toPascalCase(node.key)

        val nestedInterfaceBuilder =
            TypeSpec
                .interfaceBuilder(interfaceName)
                .addModifiers(KModifier.PUBLIC)

        val nestedClassName = context.currentClassName.nestedClass(interfaceName)

        val nestedContext =
            InterfaceGenContext(
                builder = nestedInterfaceBuilder,
                currentClassName = nestedClassName,
                fileBuilder = context.fileBuilder,
            )

        node.children.forEach { child ->
            child.accept(this, nestedContext)
        }

        context.builder.addType(nestedInterfaceBuilder.build())
        context.builder.addProperty(
            PropertySpec.builder(propertyName, nestedClassName).build(),
        )
    }

    override fun visit(
        node: Node.Collection,
        context: InterfaceGenContext,
    ) {
        if (hooks.any { it.visit(node, context) }) return

        val propertyName = toCamelCase(node.key)
        val firstItem = node.values.values.firstOrNull()

        val itemType =
            if (firstItem is Node.Object) {
                val interfaceName = toPascalCase(node.key)
                val nestedInterfaceBuilder =
                    TypeSpec
                        .interfaceBuilder(interfaceName)
                        .addModifiers(KModifier.PUBLIC)

                val nestedClassName = context.currentClassName.nestedClass(interfaceName)
                val nestedContext =
                    InterfaceGenContext(
                        builder = nestedInterfaceBuilder,
                        currentClassName = nestedClassName,
                        fileBuilder = context.fileBuilder,
                    )

                firstItem.children.forEach { child ->
                    child.accept(this, nestedContext)
                }

                context.builder.addType(nestedInterfaceBuilder.build())
                nestedClassName
            } else {
                String::class.asTypeName()
            }

        val listType = List::class.asTypeName().parameterizedBy(itemType)
        context.builder.addProperty(
            PropertySpec.builder(propertyName, listType).build(),
        )
    }

    override fun visit(
        node: Node.StringScalar,
        context: InterfaceGenContext,
    ) {
        if (hooks.any { it.visit(node, context) }) return

        context.builder.addProperty(
            PropertySpec.builder(toCamelCase(node.key), String::class).build(),
        )
    }
}
