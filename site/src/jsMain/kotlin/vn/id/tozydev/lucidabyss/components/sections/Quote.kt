package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.TextTransform
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.icons.fa.FaQuoteLeft
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.extendedBy
import com.varabyte.kobweb.silk.style.selectors.children
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.ColumnIslandVariant
import vn.id.tozydev.lucidabyss.components.widgets.Island
import vn.id.tozydev.lucidabyss.styles.ColorVars
import vn.id.tozydev.lucidabyss.styles.Text3XlModifier
import vn.id.tozydev.lucidabyss.styles.TextSmStyle
import vn.id.tozydev.lucidabyss.styles.TextXsStyle

val QuoteIslandVariant =
    ColumnIslandVariant.extendedBy {
        base {
            Modifier
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
                .backgroundColor(ColorVars.BgHighlight.value())
                .flexShrink(1)
        }
        children(".fa-quote-left") {
            Text3XlModifier
                .position(Position.Absolute)
                .top(1.cssRem)
                .left(1.cssRem)
                .color(ColorVars.TextInverse.value())
                .opacity(0.3)
        }
    }

val QuoteTextStyle =
    TextSmStyle.extendedBy {
        base {
            Modifier
                .fontWeight(FontWeight.Medium)
                .fontStyle(FontStyle.Italic)
                .textAlign(TextAlign.Center)
                .color(ColorVars.TextHeading.value())
        }
    }

val QuoteCiteStyle =
    TextXsStyle.extendedBy {
        base {
            Modifier
                .margin(top = 0.5.cssRem)
                .textTransform(TextTransform.Uppercase)
                .fontWeight(FontWeight.Bold)
        }
    }

@Composable
fun Quote(modifier: Modifier = Modifier) {
    Island(
        modifier = modifier,
        variant = QuoteIslandVariant,
    ) {
        FaQuoteLeft()
        Blockquote(QuoteTextStyle.toAttrs()) {
            Text("“I know that I know nothing”")
        }
        SpanText("— Socrates", QuoteCiteStyle.toModifier())
    }
}
