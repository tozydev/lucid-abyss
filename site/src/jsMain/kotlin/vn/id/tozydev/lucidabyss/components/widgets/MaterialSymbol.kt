package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun MaterialSymbol(
    icon: String,
    modifier: Modifier = Modifier,
) {
    Span(
        Modifier
            .tw("material-symbols-outlined")
            .attr("data-icon", icon)
            .then(modifier)
            .toAttrs(),
    ) {
        Text(icon)
    }
}
