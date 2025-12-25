package vn.id.tozydev.lucidabyss.styles

import com.varabyte.kobweb.compose.css.BoxShadow
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.hover
import org.jetbrains.compose.web.css.*
import vn.id.tozydev.lucidabyss.theme.colorScheme

val ContainerStyle =
    CssStyle {
        val colorScheme = colorScheme
        base {
            Modifier
                .padding(1.5.cssRem)
                .backgroundColor(colorScheme.surfaceContainer)
                .color(colorScheme.onSurface)
                .borderRadius(1.5.cssRem)
                .border(1.px, LineStyle.Solid, colorScheme.outline)
                .transition {
                    property(TransitionProperty.All)
                    duration(0.3.s)
                    timingFunction(TransitionTimingFunction.cubicBezier(0.25, 0.8, 0.25, 1.0))
                }
        }
        hover {
            Modifier
                .boxShadow(
                    BoxShadow.of(0.px, 4.px, 8.px, 3.px, Color.rgba(0, 0, 0, 0.15f)),
                ).border {
                    color(colorScheme.primary)
                }
        }
    }
