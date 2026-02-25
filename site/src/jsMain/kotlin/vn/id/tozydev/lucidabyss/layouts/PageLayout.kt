package vn.id.tozydev.lucidabyss.layouts

import vn.id.tozydev.lucidabyss.strings.Strings

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
    Strings.language.value = language
}

@Composable
@Layout
fun PageLayout(
    ctx: PageContext,
    content: @Composable () -> Unit,
) {
    val pageProperties = ctx.data.getValue<Page.Properties>()
    val language = ctx.data.getValue<SiteLanguage>()
    Strings.language.value = language

    // We need to observe the language flow to trigger recomposition when it changes?
    // Since Strings.current getter accesses Strings.language.value, usage of Strings.property inside Composable
    // will just read the value. It won't subscribe.
    // So we need to explicitly collect the state here to make the layout reactive to language changes if they happen dynamically.
    // However, initPageLayout sets it.
    // If we assume full page reload or router based language, initPageLayout handles it.
    // But if we change Strings.language.value dynamically without route change, we need observation.
    // Let's add observation just in case.

    val currentLanguage by Strings.language.collectAsState()
    // We don't use currentLanguage directly, but collecting it triggers recomposition of PageLayout.
    // And since children read Strings.property which delegates to Strings.current which reads Strings.language.value...
    // Wait, Strings.current reads Strings.language.value.
    // If Strings.language is a MutableStateFlow, reading .value is safe but not reactive in Compose unless collected.
    // But since we collect it here, this Scope recomposes.
    // Does it? Yes.
    // But children are in content().
    // If PageLayout recomposes, content() is recalled? Yes.

    val scrollingState = rememberScrollingState()

    val shouldHideHeaderAndNav = scrollingState.isScrollingDown && scrollingState.currentScrollY > 50
    val shouldHideBackToTop = !scrollingState.isScrollingDown || scrollingState.currentScrollY <= 300

    LaunchedEffect(pageProperties.title, currentLanguage) {
        document.title = "${pageProperties.title} | tozydev"
    }

    MetaTag("description", pageProperties.description)

    context(language) {
        SiteHeader(
            Modifier.thenIf(shouldHideHeaderAndNav) {
                Modifier.tw("-translate-y-32")
            },
        )

        BottomNavbar()

        Main({ tw("px-4 pt-26 pb-4 max-w-6xl mx-auto") }) {
            content()
        }

        SiteFooter(Modifier.tw("max-w-6xl mx-auto mb-12 p-4"))

        BackToTopButton(
            Modifier.thenIf(shouldHideBackToTop) {
                Modifier.opacity(0.0)
            },
        )
    }
}
