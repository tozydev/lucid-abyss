package vn.id.tozydev.lucidabyss.build.strings.generator

import vn.id.tozydev.lucidabyss.build.strings.Node
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import java.util.Locale

internal fun Node.Object.fullPath(): String {
    val parentObj = parent as? Node.Object
    val parentPath = if (parentObj != null && parentObj.name != "Strings") parentObj.fullPath() else ""

    val myName = toPascalCase(name)
    return if (parentPath.isNotEmpty()) "${parentPath}$myName" else myName
}

internal fun toCamelCase(s: String): String {
    val parts = s.split('-', '_', '.')
    if (parts.isEmpty()) return s
    val first = parts[0].replaceFirstChar { it.lowercase(Locale.getDefault()) }
    val rest = parts.drop(1).joinToString("") { part ->
        part.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
    return first + rest
}

internal fun toPascalCase(s: String): String {
     val parts = s.split('-', '_', '.')
     return parts.joinToString("") { part ->
         part.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
     }
}

internal fun extractArgs(values: Map<SiteLanguage, String?>): Set<String> {
    val regex = """\$\{([^}]+)\}""".toRegex()
    val allArgs = mutableSetOf<String>()
    values.values.filterNotNull().forEach { str ->
        regex.findAll(str).forEach { match ->
            allArgs.add(match.groupValues[1])
        }
    }
    return allArgs
}

internal fun String.escape(): String {
    return this.replace("\"", "\\\"").replace("$", "\\$")
}
