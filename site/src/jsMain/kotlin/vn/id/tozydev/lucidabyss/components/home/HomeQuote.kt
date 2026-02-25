package vn.id.tozydev.lucidabyss.components.home

import Res
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaQuoteLeft
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun HomeQuote(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw(
                "card bg-gray-900 text-gray-100 hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] transition-all duration-300",
            ).then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body text-sm text-center items-center justify-center") }) {
            FaQuoteLeft(Modifier.tw("text-gray-700 text-4xl absolute top-4 left-4 opacity-30"))
            Blockquote({ tw("font-medium italic") }) {
                Text(Res.string.widget_quote_text)
            }
            Span({ tw("font-bold uppercase text-gray-400") }) {
                Text(Res.string.widget_quote_author)
            }
        }
    }
}
