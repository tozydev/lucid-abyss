package vn.id.tozydev.lucidabyss.build.strings

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier

class StringTemplateHook :
    InterfaceHook,
    ObjectHook,
    AccessorHook {
    private val templateRegex = Regex("""\{([a-zA-Z0-9_]+)\}""")

    private fun extractArgs(node: Node.StringScalar): List<String> {
        val sampleText = node.value.values.firstOrNull { it.isNotEmpty() } ?: ""
        return templateRegex
            .findAll(sampleText)
            .map { it.groupValues[1] }
            .distinct()
            .toList()
    }

    override fun visit(
        node: Node.StringScalar,
        context: InterfaceGenContext,
    ): Boolean {
        val args = extractArgs(node)
        if (args.isEmpty()) return false

        val funBuilder =
            FunSpec
                .builder(toCamelCase(node.key))
                .addModifiers(KModifier.ABSTRACT)
                .returns(String::class)

        args.forEach { funBuilder.addParameter(it, String::class) }
        context.builder.addFunction(funBuilder.build())

        return true
    }

    override fun visit(
        node: Node.StringScalar,
        context: ObjectGenContext,
    ): Boolean {
        val args = extractArgs(node)
        if (args.isEmpty()) return false

        val text = node.value[context.sourceName] ?: ""
        val funBuilder =
            FunSpec
                .builder(toCamelCase(node.key))
                .addModifiers(KModifier.OVERRIDE)
                .returns(String::class)

        args.forEach { funBuilder.addParameter(it, String::class) }

        val kotlinTemplate = text.replace(templateRegex) { $$"{$${it.groupValues[1]}}" }
        funBuilder.addStatement("return %P", kotlinTemplate)

        context.builder.addFunction(funBuilder.build())

        return true
    }

    override fun visit(
        node: Node.StringScalar,
        context: AccessorGenContext,
    ): Boolean {
        val args = extractArgs(node)
        if (args.isEmpty()) return false

        val propertyName = toCamelCase(node.key)
        val funBuilder =
            FunSpec
                .builder(propertyName)
                .addModifiers(KModifier.OVERRIDE)
                .returns(String::class)

        args.forEach { funBuilder.addParameter(it, String::class) }

        val callArgs = args.joinToString(", ")
        funBuilder.addStatement("return current.$propertyName($callArgs)")

        context.builder.addFunction(funBuilder.build())

        return true
    }
}
