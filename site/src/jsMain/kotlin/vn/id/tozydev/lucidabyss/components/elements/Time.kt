package vn.id.tozydev.lucidabyss.components.elements

import androidx.compose.runtime.*
import kotlinx.browser.document
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.Element
import org.w3c.dom.HTMLSpanElement

private open class ElementBuilderImplementation<TElement : Element>(
    private val tagName: String,
) : ElementBuilder<TElement> {
    private val el: Element by lazy { document.createElement(tagName) }

    @Suppress("UNCHECKED_CAST")
    override fun create(): TElement = el.cloneNode() as TElement
}

private val Time: ElementBuilder<HTMLSpanElement> = ElementBuilderImplementation("time")

@Composable
fun Time(
    datetime: String? = null,
    attrs: AttrBuilderContext<HTMLSpanElement>? = null,
    content: ContentBuilder<HTMLSpanElement>? = null,
) = TagElement(
    elementBuilder = Time,
    applyAttrs = {
        if (datetime != null) {
            attr("datetime", datetime)
        }
        if (attrs != null) {
            attrs()
        }
    },
    content = content,
)
