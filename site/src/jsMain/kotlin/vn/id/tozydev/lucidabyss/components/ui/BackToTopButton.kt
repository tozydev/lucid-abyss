package vn.id.tozydev.lucidabyss.components.ui

import Res
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowUp
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.SMOOTH
import org.w3c.dom.ScrollBehavior
import org.w3c.dom.ScrollToOptions
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun BackToTopButton(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw("fixed bottom-24 right-6 z-50 transition duration-300 md:bottom-8 md:right-8")
            .then(modifier)
            .toAttrs(),
    ) {
        Button(
            {
                tw("btn btn-primary btn-circle")
                attr("aria-label", Res.string.widget_back_to_top_button_label)
                onClick {
                    window.scrollTo(ScrollToOptions(top = 0.0, behavior = ScrollBehavior.SMOOTH))
                }
            },
        ) {
            FaArrowUp()
        }
    }
}
