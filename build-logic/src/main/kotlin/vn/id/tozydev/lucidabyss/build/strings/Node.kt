package vn.id.tozydev.lucidabyss.build.strings

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.nodes.MappingNode
import org.yaml.snakeyaml.nodes.ScalarNode
import org.yaml.snakeyaml.nodes.SequenceNode
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import java.io.File
import org.yaml.snakeyaml.nodes.Node as YamlNode

sealed class Node(
    val name: String,
) {
    var parent: Node? = null

    class Object(
        name: String,
    ) : Node(name) {
        val children = mutableListOf<Node>()
    }

    class SimpleString(
        name: String,
        val values: Map<SiteLanguage, String?>,
    ) : Node(name)

    class MultiPartString(
        name: String,
        val values: Map<SiteLanguage, List<String>?>,
    ) : Node(name)
}

internal fun buildStringNodeTree(files: Map<SiteLanguage, File>): Node.Object {
    val root = Node.Object("Strings")
    val yaml = Yaml()

    // Parse all files to Yaml Nodes
    val roots =
        files.mapValues { (_, file) ->
            if (file.exists()) {
                file.reader().use { yaml.compose(it) }
            } else {
                null
            }
        }

    // We need to merge keys from all roots.
    // Roots should be MappingNodes.

    // Helper to get keys from a set of nodes
    fun getKeys(nodes: Map<SiteLanguage, YamlNode?>): Set<String> {
        val keys = mutableSetOf<String>()
        nodes.values.filterIsInstance<MappingNode>().forEach { mappingNode ->
            mappingNode.value.forEach { tuple ->
                val keyNode = tuple.keyNode
                if (keyNode is ScalarNode) {
                    keys.add(keyNode.value)
                }
            }
        }
        return keys
    }

    // Recursive processing
    fun process(
        parent: Node.Object,
        currentNodes: Map<SiteLanguage, YamlNode?>,
    ) {
        val keys = getKeys(currentNodes)

        keys.forEach { key ->
            // Get value node for this key for each language
            val valueNodes =
                currentNodes.mapValues { (_, node) ->
                    if (node is MappingNode) {
                        node.value.find { (it.keyNode as? ScalarNode)?.value == key }?.valueNode
                    } else {
                        null
                    }
                }

            // Determine type from first non-null node
            val firstNonNull = valueNodes.values.filterNotNull().firstOrNull() ?: return@forEach

            when (firstNonNull) {
                is MappingNode -> {
                    val childObj = Node.Object(key)
                    childObj.parent = parent
                    parent.children.add(childObj)
                    process(childObj, valueNodes)
                }

                is SequenceNode -> {
                    // List of strings
                    val values =
                        valueNodes.mapValues { (_, node) ->
                            (node as? SequenceNode)?.value?.mapNotNull { (it as? ScalarNode)?.value }
                        }
                    val childNode = Node.MultiPartString(key, values)
                    childNode.parent = parent
                    parent.children.add(childNode)
                }

                is ScalarNode -> {
                    // Simple string
                    val values =
                        valueNodes.mapValues { (_, node) ->
                            (node as? ScalarNode)?.value
                        }
                    val childNode = Node.SimpleString(key, values)
                    childNode.parent = parent
                    parent.children.add(childNode)
                }
            }
        }
    }

    process(root, roots)
    return root
}
