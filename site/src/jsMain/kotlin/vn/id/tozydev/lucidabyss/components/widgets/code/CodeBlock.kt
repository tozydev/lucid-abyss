package vn.id.tozydev.lucidabyss.components.widgets.code

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import vn.id.tozydev.lucidabyss.components.widgets.Figure
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun CodeBlock(
    code: String,
    modifier: Modifier = Modifier,
    lang: String? = null,
    header: (@Composable (code: String, lang: String?) -> Unit)? = null,
    content: @Composable (code: String, lang: String?) -> Unit = { code, lang ->
        CodeContent(code = code, lang = lang)
    },
) {
    Figure(
        Modifier
            .tw("relative rounded-xl overflow-hidden border border-outline/30 shadow-md bg-[#fafafa] dark:bg-[#282c34] not-prose my-4")
            .then(modifier)
            .toAttrs(),
    ) {
        header?.invoke(code, lang)
        content(code, lang)
    }
}
