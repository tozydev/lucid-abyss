package vn.id.tozydev.lucidabyss.components.layouts

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import kotlinx.browser.document
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.sections.BottomNavbar
import vn.id.tozydev.lucidabyss.components.sections.SiteFooter
import vn.id.tozydev.lucidabyss.components.sections.SiteHeader
import vn.id.tozydev.lucidabyss.components.widgets.BackToTopButton
import vn.id.tozydev.lucidabyss.components.widgets.MetaTag
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.utils.rememberScrollingState
import vn.id.tozydev.lucidabyss.utils.tw

const val PAGE_LAYOUT_FNQ = ".components.layouts.PageLayout"

@InitRoute
fun initPageLayout(ctx: InitRouteContext) {
    val language =
        SiteLanguage.fromCode(
            ctx.route.path
                .removePrefix("/")
                .substringBefore('/'),
        )
    ctx.data.add(language)
    SiteLanguage.current = language
}

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

    SiteHeader(
        Modifier.thenIf(shouldHideHeaderAndNav) {
            Modifier.tw("-translate-y-32")
        },
    )

    BottomNavbar()

    Main({ tw("pt-28 md:pt-32 pb-24 md:pb-20 px-4 max-w-7xl mx-auto") }) {
        content()
    }

    SiteFooter(Modifier.tw("max-w-6xl mx-auto mb-12 p-4"))

    BackToTopButton(
        Modifier.thenIf(shouldHideBackToTop) {
            Modifier.opacity(0.0)
        },
    )
}
