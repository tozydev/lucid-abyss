package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowUp
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.vars.animation.TransitionDurationVars
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.w3c.dom.SMOOTH
import org.w3c.dom.ScrollBehavior
import org.w3c.dom.ScrollToOptions
import vn.id.tozydev.lucidabyss.styles.CircleButtonVariant
import vn.id.tozydev.lucidabyss.styles.PrimaryButtonVariant

val BackToTopButtonVariant =
    ButtonStyle.addVariant {
        base {
            Modifier
                .position(Position.Fixed)
                .bottom(6.cssRem) // todo mobile nav
                .right(1.5.cssRem)
                .zIndex(50)
                .transition {
                    property(TransitionProperty.All)
                    duration(TransitionDurationVars.Slow.value())
                    timingFunction(TransitionTimingFunction.cubicBezier(0.25, 0.8, 0.25, 1.0))
                }
        }
        Breakpoint.MD {
            Modifier.bottom(2.cssRem).right(2.cssRem)
        }
        hover {
            Modifier.scale(1.05f)
        }
    }

@Composable
fun BackToTopButton(modifier: Modifier = Modifier) {
    Button(
        onClick = {
            window.scrollTo(ScrollToOptions(top = 0.0, behavior = ScrollBehavior.SMOOTH))
        },
        modifier = modifier,
        variant = CircleButtonVariant then PrimaryButtonVariant then BackToTopButtonVariant,
    ) {
        FaArrowUp()
    }
}
