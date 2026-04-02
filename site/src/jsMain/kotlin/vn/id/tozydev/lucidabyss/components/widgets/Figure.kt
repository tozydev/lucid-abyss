package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.dom.GenericTag
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement

private const val FIGURE_TAG = "figure"

@Composable
fun Figure(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: (@Composable ElementScope<HTMLDivElement>.() -> Unit)? = null,
) {
    GenericTag(name = FIGURE_TAG, attrs = attrs, content = content)
}
