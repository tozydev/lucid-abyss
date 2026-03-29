package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import kotlinx.browser.document
import org.w3c.dom.HTMLScriptElement

@Composable
fun JsonLdScript(
    id: String,
    jsonLd: String,
) {
    DisposableEffect(id, jsonLd) {
        val head = document.head ?: return@DisposableEffect onDispose { }
        val element =
            (head.querySelector("script#$id") as? HTMLScriptElement)
                ?: (document.createElement("script") as HTMLScriptElement).also {
                    it.id = id
                    it.type = "application/ld+json"
                    head.appendChild(it)
                }

        element.text = jsonLd

        onDispose {
            head.removeChild(element)
        }
    }
}
