package vn.id.tozydev.lucidabyss.components.layouts

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.layout.Layout
import kotlinx.browser.document
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.sections.SiteFooter
import vn.id.tozydev.lucidabyss.components.sections.SiteHeader
import vn.id.tozydev.lucidabyss.components.widgets.BackToTopButton
import vn.id.tozydev.lucidabyss.components.widgets.BottomNavbar
import vn.id.tozydev.lucidabyss.utils.tw

data class PageLayoutData(
    val title: String,
)

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

    SiteHeader()

    BottomNavbar()

    Main({ tw("px-4 pt-26 pb-4 max-w-6xl mx-auto") }) {
        content()
    }

    SiteFooter(Modifier.tw("max-w-6xl mx-auto mb-12 p-4"))

    BackToTopButton()
}
