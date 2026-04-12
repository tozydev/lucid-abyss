package vn.id.tozydev.lucidabyss.build.strings

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec

interface NodeVisitor<C, R> {
    fun visit(
        node: Node.Object,
        context: C,
    ): R

    fun visit(
        node: Node.Collection,
        context: C,
    ): R

    fun visit(
        node: Node.StringScalar,
        context: C,
    ): R
}

interface InterfaceHook {
    fun visit(
        node: Node.Object,
        context: InterfaceGenContext,
    ): Boolean = false

    fun visit(
        node: Node.Collection,
        context: InterfaceGenContext,
    ): Boolean = false

    fun visit(
        node: Node.StringScalar,
        context: InterfaceGenContext,
    ): Boolean = false
}

interface ObjectHook {
    fun visit(
        node: Node.Object,
        context: ObjectGenContext,
    ): Boolean = false

    fun visit(
        node: Node.Collection,
        context: ObjectGenContext,
    ): Boolean = false

    fun visit(
        node: Node.StringScalar,
        context: ObjectGenContext,
    ): Boolean = false
}

interface AccessorHook {
    fun visit(
        node: Node.Object,
        context: AccessorGenContext,
    ): Boolean = false

    fun visit(
        node: Node.Collection,
        context: AccessorGenContext,
    ): Boolean = false

    fun visit(
        node: Node.StringScalar,
        context: AccessorGenContext,
    ): Boolean = false
}

data class InterfaceGenContext(
    val builder: TypeSpec.Builder,
    val currentClassName: ClassName,
    val fileBuilder: FileSpec.Builder,
)

data class ObjectGenContext(
    val builder: TypeSpec.Builder,
    val sourceName: String,
    val currentInterfaceClassName: ClassName,
)

data class AccessorGenContext(
    val builder: TypeSpec.Builder,
    val interfaceClassName: ClassName,
)

/**
 * Represents a hierarchical merged node structure.
 */
sealed interface Node {
    val key: String
    val path: String

    fun <C, R> accept(
        visitor: NodeVisitor<C, R>,
        context: C,
    ): R

    data class Object(
        override val key: String,
        override val path: String,
        val children: List<Node>,
    ) : Node {
        override fun <C, R> accept(
            visitor: NodeVisitor<C, R>,
            context: C,
        ) = visitor.visit(this, context)
    }

    data class Collection(
        override val key: String,
        override val path: String,
        val values: Map<String, Node>,
    ) : Node {
        override fun <C, R> accept(
            visitor: NodeVisitor<C, R>,
            context: C,
        ) = visitor.visit(this, context)
    }

    data class StringScalar(
        override val key: String,
        override val path: String,
        val value: Map<String, String>,
    ) : Node {
        override fun <C, R> accept(
            visitor: NodeVisitor<C, R>,
            context: C,
        ) = visitor.visit(this, context)
    }
}
