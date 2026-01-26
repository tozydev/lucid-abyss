package vn.id.tozydev.lucidabyss.components.seo

import androidx.compose.runtime.*
import kotlinx.browser.document
import org.w3c.dom.HTMLMetaElement

@Composable
fun MetaTag(
    name: String,
    content: String,
) {
    DisposableEffect(name, content) {
        val head = document.head!!
        val element =
            (
                head.querySelector("meta[name='$name']") ?: document.createElement("meta").apply {
                    this as HTMLMetaElement
                    this.name = name
                    head.appendChild(this)
                }
            ).apply {
                this as HTMLMetaElement
                this.content = content
            }

        onDispose {
            head.removeChild(element)
        }
    }
}
