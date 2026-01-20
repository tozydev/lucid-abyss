package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaComments
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun Discussion(modifier: Modifier = Modifier) {
    Div(Modifier.tw("card card-border bg-base-100 card-lg").then(modifier).toAttrs()) {
        Div({ tw("card-body") }) {
            H3({ tw("card-title") }) {
                Text(Res.string.widget_discussion_title)
            }
            Div({ tw("text-sm flex flex-col gap-2 text-center bg-base-200 p-8 rounded-box") }) {
                FaComments(Modifier.tw("text-4xl"))
                Text(Res.string.widget_discussion_description)
            }
        }
    }
}
