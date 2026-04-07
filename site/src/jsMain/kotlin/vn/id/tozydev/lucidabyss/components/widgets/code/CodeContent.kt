package vn.id.tozydev.lucidabyss.components.widgets.code

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement
import vn.id.tozydev.lucidabyss.utils.shiki.highlightCode
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun CodeContent(
    code: String,
    modifier: Modifier = Modifier,
    lang: String? = null,
) {
    var highlightedCode by remember { mutableStateOf("<pre><code>$code</code></pre>") }

    LaunchedEffect(code) {
        highlightedCode = highlightCode(code, lang ?: "text")
    }

    Div(
        Modifier
            .tw("p-4 overflow-x-auto")
            .then(modifier)
            .toAttrs {
                prop<HTMLDivElement, String>(
                    { element, value -> element.innerHTML = value },
                    highlightedCode,
                )
            },
    )
}
