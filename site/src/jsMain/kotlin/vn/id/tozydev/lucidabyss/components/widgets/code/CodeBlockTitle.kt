package vn.id.tozydev.lucidabyss.components.widgets.code

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun CodeBlockTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Div(Modifier.tw("flex items-center gap-2 sm:gap-3 px-3 pt-3").then(modifier).toAttrs()) {
        Div(
            {
                tw(
                    "flex items-center gap-2 px-2 py-1 bg-surface-container-lowest rounded-lg border border-outline/30 text-xs font-medium font-mono text-on-surface shadow-sm cursor-default select-none",
                )
            },
        ) {
            Text(title)
        }
    }
}
