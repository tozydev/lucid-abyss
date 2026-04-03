package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import vn.id.tozydev.lucidabyss.utils.tw
import org.jetbrains.compose.web.dom.Table as JbTable

@Composable
fun Table(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Figure(Modifier.tw("overflow-x-auto rounded-xl").then(modifier).toAttrs()) {
        JbTable {
            content()
        }
    }
}
