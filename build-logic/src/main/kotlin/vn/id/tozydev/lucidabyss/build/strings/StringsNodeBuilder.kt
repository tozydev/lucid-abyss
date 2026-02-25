package vn.id.tozydev.lucidabyss.build.strings

import vn.id.tozydev.lucidabyss.core.SiteLanguage

internal fun buildStringNodeTree(data: Map<SiteLanguage, Map<String, Any>>): Node.Object {
    val root = Node.Object("Strings")
    val allKeys = data.values.flatMap { it.keys }.toSet()

    allKeys.forEach { key ->
        processKey(root, key, data)
    }

    return root
}

private fun processKey(parent: Node.Object, key: String, data: Map<SiteLanguage, Map<String, Any>>) {
    // Collect values for this key across all languages
    val values: Map<SiteLanguage, Any?> = data.mapValues { it.value[key] }

    // Find the first non-null value to determine the type
    val firstNonNullValue = values.values.filterNotNull().firstOrNull() ?: return

    when (firstNonNullValue) {
        is Map<*, *> -> {
            val node = Node.Object(key)
            node.parent = parent
            parent.children.add(node)

            // Safe cast and recursion
            // We create a new data map for the next level: Map<SiteLanguage, Map<String, Any>>
            val childData = values.mapValues { (_, value) ->
                @Suppress("UNCHECKED_CAST")
                value as? Map<String, Any> ?: emptyMap()
            }

            val allChildKeys = childData.values.flatMap { it.keys }.toSet()
            allChildKeys.forEach { childKey ->
                processKey(node, childKey, childData)
            }
        }
        is List<*> -> {
            // Safe conversion to List<String>
            val listValues = values.mapValues { (_, value) ->
                (value as? List<*>)?.map { it.toString() }
            }
            val node = Node.MultiPartString(key, listValues)
            node.parent = parent
            parent.children.add(node)
        }
        else -> { // String or other primitives treated as String
            val stringValues = values.mapValues { (_, value) ->
                value?.toString()
            }
            val node = Node.SimpleString(key, stringValues)
            node.parent = parent
            parent.children.add(node)
        }
    }
}
