package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowUp
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.w3c.dom.SMOOTH
import org.w3c.dom.ScrollBehavior
import org.w3c.dom.ScrollToOptions

val BackToTopButtonVariant =
    ButtonStyle.addVariant {
        base {
            Modifier
                .position(Position.Fixed)
                .bottom(6.cssRem) // todo mobile nav
                .right(1.5.cssRem)
                .zIndex(50)
        }
        Breakpoint.MD {
            Modifier.bottom(2.cssRem).right(2.cssRem)
        }
    }

@Composable
fun BackToTopButton(modifier: Modifier = Modifier) {
    Button(
        onClick = {
            window.scrollTo(ScrollToOptions(top = 0.0, behavior = ScrollBehavior.SMOOTH))
        },
        modifier = modifier,
        variant = BackToTopButtonVariant,
    ) {
        FaArrowUp()
    }
}
