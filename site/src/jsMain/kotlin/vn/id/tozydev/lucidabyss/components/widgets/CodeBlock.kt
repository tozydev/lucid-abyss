package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*

@Composable
fun CodeBlock(
    code: String,
    modifier: Modifier = Modifier,
    lang: String? = null,
) {
    Pre(
        Modifier.then(modifier).toAttrs(),
    ) {
        Code(
            {
                classes("language-${lang ?: "none"}")
            },
        ) {
            Text(code)
        }
    }
}
