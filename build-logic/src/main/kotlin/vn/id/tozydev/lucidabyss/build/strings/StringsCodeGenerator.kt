package vn.id.tozydev.lucidabyss.build.strings

import vn.id.tozydev.lucidabyss.core.SiteLanguage
import java.util.Locale

internal fun generateStringsCode(packageName: String, structure: GenerateStringsTask.Node.Object): String {
    val sb = StringBuilder()
    sb.appendLine("package $packageName")
    sb.appendLine()
    sb.appendLine("import androidx.compose.runtime.getValue")
    sb.appendLine("import androidx.compose.runtime.mutableStateOf")
    sb.appendLine("import androidx.compose.runtime.setValue")
    sb.appendLine("import androidx.compose.runtime.Composable")
    sb.appendLine("import vn.id.tozydev.lucidabyss.core.SiteLanguage")
    sb.appendLine()

    // Generate interface
    sb.appendLine("interface Strings {")
    structure.children.forEach { child ->
        generateInterfaceMember(child, sb, "    ")
    }
    sb.appendLine("}")
    sb.appendLine()

    // Generate nested interfaces
    generateNestedInterfaces(structure, sb)

    // Generate implementations
    SiteLanguage.entries.forEach { language ->
        sb.appendLine("object Strings${language.name} : Strings {")
        structure.children.forEach { child ->
             generateImplementationMember(child, language, sb, "    ")
        }
        sb.appendLine("}")
        sb.appendLine()
    }

    // Generate Strings object accessor
    sb.appendLine("object Strings : Strings {")
    sb.appendLine("    var language by mutableStateOf(SiteLanguage.Default)")
    sb.appendLine()
    sb.appendLine("    val current: Strings")
    sb.appendLine("        get() = when(language) {")
    SiteLanguage.entries.forEach { language ->
        sb.appendLine("            SiteLanguage.${language.name} -> Strings${language.name}")
    }
    sb.appendLine("        }")
    sb.appendLine()

    sb.appendLine("    inline fun <T> withLanguage(lang: SiteLanguage, block: () -> T): T {")
    sb.appendLine("        val old = language")
    sb.appendLine("        language = lang")
    sb.appendLine("        try {")
    sb.appendLine("            return block()")
    sb.appendLine("        } finally {")
    sb.appendLine("            language = old")
    sb.appendLine("        }")
    sb.appendLine("    }")
    sb.appendLine()

    structure.children.forEach { child ->
        generateDelegateMember(child, sb, "    ")
    }
    sb.appendLine("}")

    return sb.toString()
}

private fun generateInterfaceMember(node: GenerateStringsTask.Node, sb: StringBuilder, indent: String) {
    val kotlinName = toCamelCase(node.name)
    when (node) {
        is GenerateStringsTask.Node.Object -> {
            val typeName = node.fullPath()
            sb.appendLine("${indent}val $kotlinName: $typeName")
        }
        is GenerateStringsTask.Node.SimpleString -> {
            val args = extractArgs(node.values)
            if (args.isEmpty()) {
                sb.appendLine("${indent}val $kotlinName: String")
            } else {
                val argsDecl = args.joinToString(", ") { "$it: String" }
                sb.appendLine("${indent}fun $kotlinName($argsDecl): String")
            }
        }
        is GenerateStringsTask.Node.MultiPartString -> {
            val defaultList = node.values[SiteLanguage.Default] ?: emptyList()
            val size = defaultList.size
            if (size > 0) {
                val lambdaArgs = (1..size).joinToString(", ") { "String" }
                sb.appendLine("${indent}fun <T> $kotlinName(render: ($lambdaArgs) -> T): T")
            }
        }
    }
}

private fun generateNestedInterfaces(node: GenerateStringsTask.Node.Object, sb: StringBuilder) {
    node.children.filterIsInstance<GenerateStringsTask.Node.Object>().forEach { child ->
        val typeName = child.fullPath()
        sb.appendLine("interface $typeName {")
        child.children.forEach { grandChild ->
             generateInterfaceMember(grandChild, sb, "    ")
        }
        sb.appendLine("}")
        sb.appendLine()
        generateNestedInterfaces(child, sb)
    }
}

private fun generateImplementationMember(node: GenerateStringsTask.Node, language: SiteLanguage, sb: StringBuilder, indent: String) {
    val kotlinName = toCamelCase(node.name)
    when (node) {
        is GenerateStringsTask.Node.Object -> {
            val typeName = node.fullPath()
            sb.appendLine("${indent}override val $kotlinName: $typeName = object : $typeName {")
            node.children.forEach { child ->
                generateImplementationMember(child, language, sb, indent + "    ")
            }
            sb.appendLine("${indent}}")
        }
        is GenerateStringsTask.Node.SimpleString -> {
            val value = (node.values[language] ?: "").escape()
            val args = extractArgs(node.values)
            if (args.isEmpty()) {
                sb.appendLine("${indent}override val $kotlinName: String = \"$value\"")
            } else {
                val argsDecl = args.joinToString(", ") { "$it: String" }
                sb.appendLine("${indent}override fun $kotlinName($argsDecl): String {")
                sb.appendLine("${indent}    var result = \"$value\"")
                 args.forEach { arg ->
                     sb.appendLine("${indent}    result = result.replace(\"\\$\" + \"{$arg}\", $arg)")
                 }
                sb.appendLine("${indent}    return result")
                sb.appendLine("${indent}}")
            }
        }
        is GenerateStringsTask.Node.MultiPartString -> {
             val defaultList = node.values[SiteLanguage.Default] ?: emptyList()
             val size = defaultList.size
             if (size > 0) {
                val lambdaArgs = (1..size).joinToString(", ") { "String" }
                val valueArgs = (0 until size).joinToString(", ") { "parts[$it]" }

                val list = node.values[language] ?: emptyList()
                val paddedList = list + List(size - list.size) { "" }
                val listString = paddedList.take(size).joinToString(", ") { "\"${it.escape()}\"" }

                sb.appendLine("${indent}override fun <T> $kotlinName(render: ($lambdaArgs) -> T): T {")
                sb.appendLine("${indent}    val parts = listOf($listString)")
                sb.appendLine("${indent}    return render($valueArgs)")
                sb.appendLine("${indent}}")
             }
        }
    }
}

private fun generateDelegateMember(node: GenerateStringsTask.Node, sb: StringBuilder, indent: String) {
    val kotlinName = toCamelCase(node.name)
    when (node) {
        is GenerateStringsTask.Node.Object -> {
            val typeName = node.fullPath()
             sb.appendLine("${indent}override val $kotlinName: $typeName")
             sb.appendLine("${indent}    get() = current.$kotlinName")
        }
        is GenerateStringsTask.Node.SimpleString -> {
            val args = extractArgs(node.values)
            if (args.isEmpty()) {
                sb.appendLine("${indent}override val $kotlinName: String")
                sb.appendLine("${indent}    get() = current.$kotlinName")
            } else {
                 val argsDecl = args.joinToString(", ") { "$it: String" }
                 val argsCall = args.joinToString(", ") { it }
                 sb.appendLine("${indent}override fun $kotlinName($argsDecl): String {")
                 sb.appendLine("${indent}    return current.$kotlinName($argsCall)")
                 sb.appendLine("${indent}}")
            }
        }
        is GenerateStringsTask.Node.MultiPartString -> {
            val defaultList = node.values[SiteLanguage.Default] ?: emptyList()
            val size = defaultList.size
            if (size > 0) {
                 val lambdaArgs = (1..size).joinToString(", ") { "String" }
                 sb.appendLine("${indent}override fun <T> $kotlinName(render: ($lambdaArgs) -> T): T {")
                 sb.appendLine("${indent}    return current.$kotlinName(render)")
                 sb.appendLine("${indent}}")
            }
        }
    }
}

private fun GenerateStringsTask.Node.Object.fullPath(): String {
    val parentObj = parent as? GenerateStringsTask.Node.Object
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
