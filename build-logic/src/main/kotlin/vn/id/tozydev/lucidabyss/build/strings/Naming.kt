package vn.id.tozydev.lucidabyss.build.strings

import java.util.Locale

internal fun toPascalCase(s: String): String {
    val parts = s.split('-', '_', '.')
    return parts.joinToString("") { part ->
        part.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
    }
}

internal fun toCamelCase(s: String): String {
    val pascalCase = toPascalCase(s)
    if (pascalCase.isEmpty()) return s
    return pascalCase.replaceFirstChar { it.lowercase(Locale.getDefault()) }
}
