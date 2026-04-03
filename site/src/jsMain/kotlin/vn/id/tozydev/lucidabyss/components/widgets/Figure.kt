package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.dom.GenericTag
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement

private const val FIGURE_TAG = "figure"

@Composable
fun Figure(
    attrs: AttrBuilderContext<HTMLElement>? = null,
    content: (@Composable ElementScope<HTMLElement>.() -> Unit)? = null,
) {
    GenericTag(name = FIGURE_TAG, attrs = attrs, content = content)
}
