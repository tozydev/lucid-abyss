package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.BoxShadow
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.dom.ElementRefScope
import com.varabyte.kobweb.compose.dom.registerRefScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.ComponentKind
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.CssStyleVariant
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.addVariantBase
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement

object IslandVars {
    val TextColorVar by StyleVariable<CSSColorValue>()

    val BackgroundColorVar by StyleVariable<CSSColorValue>()
    val BackgroundHoverColorVar by StyleVariable(BackgroundColorVar.value())

    val BorderColorVar by StyleVariable<CSSColorValue>()
    val BorderHoverColorVar by StyleVariable<CSSColorValue>()

    val BoxShadowVar by StyleVariable<BoxShadow>()
    val BoxShadowHoverVar by StyleVariable<BoxShadow>()
}

sealed interface IslandKind : ComponentKind

val IslandStyle =
    CssStyle<IslandKind> {
        base {
            Modifier
                .backgroundColor(IslandVars.BackgroundColorVar.value())
                .color(IslandVars.TextColorVar.value())
                .borderRadius(1.5.cssRem)
                .border(1.px, LineStyle.Solid, IslandVars.BorderColorVar.value())
                .transition {
                    property(TransitionProperty.All)
                    duration(0.3.s)
                    timingFunction(TransitionTimingFunction.cubicBezier(0.25, 0.8, 0.25, 1.0))
                }.position(Position.Relative)
                .overflow(Overflow.Hidden)
                .padding(2.cssRem)
                .boxShadow(IslandVars.BoxShadowVar.value())
        }

        hover {
            Modifier
                .background(IslandVars.BackgroundHoverColorVar.value())
                .border {
                    color(IslandVars.BorderHoverColorVar.value())
                }.boxShadow(IslandVars.BoxShadowHoverVar.value())
        }
    }

val RowIslandVariant =
    IslandStyle.addVariantBase {
        Modifier
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Row)
    }

val ColumnIslandVariant =
    IslandStyle.addVariantBase {
        Modifier
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
    }

val NoPaddingIslandVariant =
    IslandStyle.addVariantBase {
        Modifier.padding(0.px)
    }

val SoftLiftingIslandVariant =
    IslandStyle.addVariant {
        hover {
            Modifier.translateY((-4).px)
        }
    }

@Composable
fun Island(
    modifier: Modifier = Modifier,
    variant: CssStyleVariant<IslandKind>? = null,
    ref: ElementRefScope<HTMLElement>? = null,
    content: @Composable () -> Unit,
) {
    Div(
        IslandStyle
            .toModifier(variant)
            .then(modifier)
            .toAttrs(),
    ) {
        registerRefScope(ref)
        content()
    }
}
