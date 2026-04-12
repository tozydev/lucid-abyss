package vn.id.tozydev.lucidabyss.build.strings

import org.yaml.snakeyaml.nodes.MappingNode
import org.yaml.snakeyaml.nodes.ScalarNode
import org.yaml.snakeyaml.nodes.SequenceNode
import org.yaml.snakeyaml.nodes.Node as YamlNode

internal fun buildNodeTreeFromYaml(sources: Map<String, YamlNode>): Node.Object = mergeNodes("", "", sources) as Node.Object

private fun mergeNodes(
    key: String,
    path: String,
    nodesBySource: Map<String, YamlNode>,
): Node {
    val firstNode =
        nodesBySource.values.firstOrNull()
            ?: return Node.Object(key, path, emptyList())

    return when (firstNode) {
        is MappingNode -> {
            val allKeys = mutableSetOf<String>()
            val childrenBySourceAndKey = mutableMapOf<String, MutableMap<String, YamlNode>>()

            for ((sourceName, node) in nodesBySource) {
                if (node is MappingNode) {
                    for (tuple in node.value) {
                        val childKey = (tuple.keyNode as? ScalarNode)?.value ?: continue
                        allKeys.add(childKey)

                        val sourceMap = childrenBySourceAndKey.getOrPut(childKey) { mutableMapOf() }
                        sourceMap[sourceName] = tuple.valueNode
                    }
                }
            }

            val children =
                allKeys.map { childKey ->
                    val childPath = if (path.isEmpty()) childKey else "$path.$childKey"
                    val childSources = childrenBySourceAndKey[childKey] ?: emptyMap()
                    mergeNodes(childKey, childPath, childSources)
                }

            Node.Object(key, path, children)
        }

        is SequenceNode -> {
            val maxIndex =
                nodesBySource.values
                    .filterIsInstance<SequenceNode>()
                    .maxOfOrNull { it.value.size } ?: 0

            val values = mutableMapOf<String, Node>()

            for (i in 0 until maxIndex) {
                val childKey = i.toString()
                val childPath = if (path.isEmpty()) childKey else "$path[$i]"

                val itemsBySource = mutableMapOf<String, YamlNode>()

                for ((sourceName, node) in nodesBySource) {
                    if (node is SequenceNode && i < node.value.size) {
                        itemsBySource[sourceName] = node.value[i]
                    }
                }

                if (itemsBySource.isNotEmpty()) {
                    values[childKey] = mergeNodes(childKey, childPath, itemsBySource)
                }
            }

            Node.Collection(key, path, values)
        }

        is ScalarNode -> {
            val stringValues = mutableMapOf<String, String>()
            for ((sourceName, node) in nodesBySource) {
                if (node is ScalarNode) {
                    stringValues[sourceName] = node.value
                }
            }
            Node.StringScalar(key, path, stringValues)
        }

        else -> {
            error("Unsupported YAML node type: ${firstNode::class.java.simpleName} at path: $path")
        }
    }
}
