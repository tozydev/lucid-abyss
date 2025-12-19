package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.BoxShadow.Companion.of
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import vn.id.tozydev.lucidabyss.theme.toColorScheme

val ContainerStyle =
    CssStyle {
        val colorScheme = colorMode.toColorScheme()
        base {
            Modifier
                .padding(1.5.cssRem)
                .backgroundColor(colorScheme.surfaceContainer)
                .color(colorScheme.onSurface)
                .borderRadius(2.cssRem)
                .border(1.px, LineStyle.Solid, colorScheme.outline)
                .transition {
                    property(TransitionProperty.All)
                    duration(0.3.s)
                    timingFunction(TransitionTimingFunction.cubicBezier(0.25, 0.8, 0.25, 1.0))
                }
        }
        hover {
            Modifier
                .transform {
                    scale(1.02)
                }.boxShadow(
                    of(0.px, 4.px, 8.px, 3.px, Color.rgba(0, 0, 0, 0.15f)),
                ).border {
                    color(colorScheme.primary)
                }
        }
    }

@Composable
fun Container(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(ContainerStyle.toModifier().then(modifier)) {
        content()
    }
}
