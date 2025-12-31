package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.BoxShadow
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.dom.ElementRefScope
import com.varabyte.kobweb.compose.dom.registerRefScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.ComponentKind
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.CssStyleVariant
import com.varabyte.kobweb.silk.style.addVariantBase
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement

sealed interface IslandKind : ComponentKind

val IslandStyle =
    CssStyle<IslandKind> {
        base {
            Modifier
                .backgroundColor(Colors.White) // todo: use theme color
                .color(Colors.Black) // todo: use theme color
                .borderRadius(1.5.cssRem) // todo: use theme radius
                .border(1.px, LineStyle.Solid, Colors.LightGray) // todo: use theme color
                .transition {
                    property(TransitionProperty.All)
                    duration(0.3.s)
                    timingFunction(TransitionTimingFunction.cubicBezier(0.25, 0.8, 0.25, 1.0))
                }.position(Position.Relative)
                .overflow(Overflow.Hidden)
                .padding(2.cssRem)
        }

        hover {
            Modifier
                .translateY((-4).px)
                .boxShadow(
                    BoxShadow.of(0.px, 12.px, 24.px, (-8).px, Color.rgba(0, 0, 0, 0.08f)),
                ).border {
                    color(Colors.Gray)
                }
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
