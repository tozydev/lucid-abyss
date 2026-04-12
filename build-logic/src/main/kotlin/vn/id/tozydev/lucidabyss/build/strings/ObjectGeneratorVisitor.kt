package vn.id.tozydev.lucidabyss.build.strings

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName

class ObjectGeneratorVisitor(
    private val hooks: List<ObjectHook> = emptyList(),
) : NodeVisitor<ObjectGenContext, Unit> {
    override fun visit(
        node: Node.Object,
        context: ObjectGenContext,
    ) {
        if (hooks.any { it.visit(node, context) }) return

        val propertyName = toCamelCase(node.key)
        val interfaceName = toPascalCase(node.key)
        val typeName = context.currentInterfaceClassName.nestedClass(interfaceName)

        val anonymousObjBuilder =
            TypeSpec
                .anonymousClassBuilder()
                .addSuperinterface(typeName)

        val nestedContext = ObjectGenContext(anonymousObjBuilder, context.sourceName, typeName)

        node.children.forEach { child ->
            child.accept(this, nestedContext)
        }

        context.builder.addProperty(
            PropertySpec
                .builder(propertyName, typeName)
                .addModifiers(KModifier.OVERRIDE)
                .initializer("%L", anonymousObjBuilder.build())
                .build(),
        )
    }

    override fun visit(
        node: Node.Collection,
        context: ObjectGenContext,
    ) {
        if (hooks.any { it.visit(node, context) }) return

        val propertyName = toCamelCase(node.key)
        val firstItem = node.values.values.firstOrNull()

        if (firstItem is Node.Object) {
            val interfaceName = toPascalCase(node.key)
            val itemType = context.currentInterfaceClassName.nestedClass(interfaceName)
            val listType = List::class.asTypeName().parameterizedBy(itemType)

            val anonymousObjects =
                node.values.values.map { itemNode ->
                    val objBuilder =
                        TypeSpec
                            .anonymousClassBuilder()
                            .addSuperinterface(itemType)

                    val nestedContext = ObjectGenContext(objBuilder, context.sourceName, itemType)

                    (itemNode as Node.Object).children.forEach { child ->
                        child.accept(this, nestedContext)
                    }

                    objBuilder.build()
                }

            val format = anonymousObjects.joinToString(",\n") { "%L" }
            context.builder.addProperty(
                PropertySpec
                    .builder(propertyName, listType)
                    .addModifiers(KModifier.OVERRIDE)
                    .initializer("listOf(\n$format\n)", *anonymousObjects.toTypedArray())
                    .build(),
            )
        } else {
            val listType = List::class.asTypeName().parameterizedBy(String::class.asTypeName())

            val strings =
                node.values.values.map { itemNode ->
                    (itemNode as? Node.StringScalar)?.value?.get(context.sourceName) ?: ""
                }

            val format = strings.joinToString(", ") { "%S" }
            context.builder.addProperty(
                PropertySpec
                    .builder(propertyName, listType)
                    .addModifiers(KModifier.OVERRIDE)
                    .initializer("listOf($format)", *strings.toTypedArray())
                    .build(),
            )
        }
    }

    override fun visit(
        node: Node.StringScalar,
        context: ObjectGenContext,
    ) {
        if (hooks.any { it.visit(node, context) }) return

        val propertyName = toCamelCase(node.key)
        val text = node.value[context.sourceName] ?: ""

        context.builder.addProperty(
            PropertySpec
                .builder(propertyName, String::class)
                .addModifiers(KModifier.OVERRIDE)
                .initializer("%S", text)
                .build(),
        )
    }
}
