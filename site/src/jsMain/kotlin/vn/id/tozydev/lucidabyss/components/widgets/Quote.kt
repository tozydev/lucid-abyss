package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaQuoteLeft
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun Quote(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw("card bg-gray-900 text-gray-100")
            .then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body text-sm text-center items-center") }) {
            FaQuoteLeft(Modifier.tw("text-gray-700 text-4xl absolute top-4 left-4 opacity-30"))
            Blockquote({ tw("font-medium italic") }) {
                Text("“I know that I know nothing”")
            }
            Span({ tw("font-bold uppercase text-gray-400") }) {
                Text("— Socrates")
            }
        }
    }
}
