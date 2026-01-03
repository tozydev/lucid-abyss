package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.TextOverflow
import com.varabyte.kobweb.compose.css.TextTransform
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.ComponentKind
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.CssStyleVariant
import com.varabyte.kobweb.silk.style.addVariantBase
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.size.BorderRadiusVars
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

object BadgeVars {
    val Color by StyleVariable<CSSColorValue>()
    val BackgroundColor by StyleVariable<CSSColorValue>()
    val BorderColor by StyleVariable<CSSColorValue>()
}

sealed interface BadgeKind : ComponentKind

val BadgeStyle =
    CssStyle<BadgeKind> {
        base {
            Modifier
                .fontWeight(FontWeight.SemiBold)
                .color(BadgeVars.Color.value())
                .backgroundColor(BadgeVars.BackgroundColor.value())
                .borderRadius(BorderRadiusVars.XS.value())
                .border(1.px, LineStyle.Solid, BadgeVars.BorderColor.value())
                .padding(0.25.cssRem, 0.5.cssRem)
                .textAlign(TextAlign.Center)
                .textOverflow(TextOverflow.Clip)
                .whiteSpace(WhiteSpace.NoWrap)
                .textTransform(TextTransform.Uppercase)
        }
    }

val NoneTransformBadgeVariant =
    BadgeStyle.addVariantBase {
        Modifier.textTransform(TextTransform.None)
    }

@Composable
fun Badge(
    modifier: Modifier = Modifier,
    variant: CssStyleVariant<BadgeKind>? = null,
    content: @Composable () -> Unit,
) {
    Span(BadgeStyle.toModifier(variant).then(modifier).toAttrs()) {
        content()
    }
}
