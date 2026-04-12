package vn.id.tozydev.lucidabyss.components.layouts

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.layout.Layout
import kotlinx.browser.document
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.sections.SiteFooter
import vn.id.tozydev.lucidabyss.components.sections.SiteHeader
import vn.id.tozydev.lucidabyss.components.widgets.BackToTopButton
import vn.id.tozydev.lucidabyss.components.widgets.BottomNavbar
import vn.id.tozydev.lucidabyss.components.widgets.MetaTag
import vn.id.tozydev.lucidabyss.utils.rememberScrollingState
import vn.id.tozydev.lucidabyss.utils.tw

const val PAGE_LAYOUT_FNQ = ".components.layouts.PageLayout"

@Layout
@Composable
fun PageLayout(
    ctx: PageContext,
    content: @Composable () -> Unit,
) {
    val pageProperties = ctx.data.getValue<PageProperties>()

    val scrollingState = rememberScrollingState()

    val shouldHideHeaderAndNav = scrollingState.isScrollingDown && scrollingState.currentScrollY > 50
    val shouldHideBackToTop = !scrollingState.isScrollingDown || scrollingState.currentScrollY <= 300

    LaunchedEffect(pageProperties.title) {
        document.title = "${pageProperties.title} | tozydev"
    }

    MetaTag("description", pageProperties.description)

    Div {
        SiteHeader(
            Modifier
                .tw("transition-transform duration-300")
                .thenIf(shouldHideHeaderAndNav) {
                    Modifier.tw("-translate-y-32")
                },
        )

        BottomNavbar(Modifier.tw("md:hidden"))

        Main(
            {
                id("main-content")
                tw("pt-24 md:pt-28 pb-16 md:pb-20 px-4 md:px-8 max-w-7xl mx-auto")
            },
        ) {
            content()
        }

        SiteFooter(Modifier.tw("max-w-7xl mx-auto mb-16 md:mb-12 p-4 md:p-8"))

        BackToTopButton(
            Modifier.thenIf(shouldHideBackToTop) {
                Modifier.opacity(0.0)
            },
        )
    }
}
