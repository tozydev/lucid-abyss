package vn.id.tozydev.lucidabyss.layouts

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
import io.github.skeptick.libres.LibresSettings
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.scaffold.BottomNavbar
import vn.id.tozydev.lucidabyss.components.scaffold.SiteFooter
import vn.id.tozydev.lucidabyss.components.scaffold.SiteHeader
import vn.id.tozydev.lucidabyss.components.seo.MetaTag
import vn.id.tozydev.lucidabyss.components.ui.BackToTopButton
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.pages.Page
import vn.id.tozydev.lucidabyss.utils.rememberScrollingState
import vn.id.tozydev.lucidabyss.utils.tw

@InitRoute
fun initPageLayout(ctx: InitRouteContext) {
    val language =
        SiteLanguage.fromCode(
            ctx.route.path
                .removePrefix("/")
                .substringBefore('/'),
        )
    ctx.data.add(language)
    LibresSettings.languageCode = language.code
}

@Composable
@Layout
fun PageLayout(
    ctx: PageContext,
    content: @Composable () -> Unit,
) {
    val pageProperties = ctx.data.getValue<Page.Properties>()
    val language = ctx.data.getValue<SiteLanguage>()
    val scrollingState = rememberScrollingState()

    val shouldHideHeaderAndNav = scrollingState.isScrollingDown && scrollingState.currentScrollY > 50

    LaunchedEffect(pageProperties.title) {
        document.title = "${pageProperties.title} | tozydev"
    }

    MetaTag("description", pageProperties.description)

    context(language) {
        SiteHeader(
            Modifier.thenIf(shouldHideHeaderAndNav) {
                Modifier.translateY((-8).cssRem)
            },
        )

        BottomNavbar()

        Main({ tw("px-4 pt-26 pb-4 max-w-6xl mx-auto") }) {
            content()
        }

        SiteFooter(Modifier.tw("max-w-6xl mx-auto mb-12 p-4"))

        BackToTopButton()
    }
}
