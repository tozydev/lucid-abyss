package vn.id.tozydev.lucidabyss.components.layouts

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.autoLength
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toAttrs
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.sections.PageFooter
import vn.id.tozydev.lucidabyss.components.sections.PageHeader
import vn.id.tozydev.lucidabyss.components.widgets.BackToTopButton
import vn.id.tozydev.lucidabyss.components.widgets.BottomNavigation

data class PageLayoutData(
    val title: String,
)

val PageLayoutStyle =
    CssStyle {
        base {
            Modifier
                .fillMaxWidth()
                .minHeight(100.vh)
                .padding(leftRight = 1.cssRem, top = 7.cssRem, bottom = 3.cssRem) // todo: use header height
                .maxWidth(72.cssRem)
                .margin(leftRight = autoLength)
        }
    }

private val layoutModifier =
    Modifier
        .fillMaxWidth()
        .maxWidth(72.cssRem)
        .margin(leftRight = autoLength)

val PageMainStyle =
    CssStyle {
        base {
            layoutModifier
                .padding(leftRight = 1.cssRem, top = 7.cssRem, bottom = 1.5.cssRem) // todo: use header height
        }
    }

@Composable
@Layout
fun PageLayout(
    ctx: PageContext,
    content: @Composable () -> Unit,
) {
    val data = ctx.data.getValue<PageLayoutData>()
    LaunchedEffect(data.title) {
        document.title = "${data.title} | tozydev"
    }

    PageHeader()

    BottomNavigation()

    Main(PageMainStyle.toAttrs()) {
        content()
    }

    PageFooter(layoutModifier)

    BackToTopButton()
}
