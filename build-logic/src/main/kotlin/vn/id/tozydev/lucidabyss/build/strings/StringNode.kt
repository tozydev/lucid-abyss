package vn.id.tozydev.lucidabyss.build.strings

import vn.id.tozydev.lucidabyss.core.SiteLanguage

sealed class Node(val name: String) {
    var parent: Node? = null

    class Object(name: String) : Node(name) {
        val children = mutableListOf<Node>()
    }
    class SimpleString(name: String, val values: Map<SiteLanguage, String?>) : Node(name)
    class MultiPartString(name: String, val values: Map<SiteLanguage, List<String>?>) : Node(name)
}
