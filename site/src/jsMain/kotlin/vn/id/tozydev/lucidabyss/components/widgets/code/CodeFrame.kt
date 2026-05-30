package vn.id.tozydev.lucidabyss.components.widgets.code

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.Figure
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun CodeFrame(
    code: String,
    modifier: Modifier = Modifier,
    lang: String? = null,
    header: (@Composable (code: String, lang: String?) -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Figure(
        Modifier
            .tw(
                "surface-island bg-surface-container-high relative overflow-hidden border border-outline/10 not-prose my-4",
            ).then(modifier)
            .toAttrs(),
    ) {
        header?.invoke(code, lang)
        Div({ tw("p-4 overflow-x-auto") }) {
            content()
        }
    }
}
