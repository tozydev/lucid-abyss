package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.DarkModeIcon
import vn.id.tozydev.lucidabyss.components.widgets.LightModeIcon
import vn.id.tozydev.lucidabyss.components.widgets.RoutineIcon
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.styles.ThemeMode
import vn.id.tozydev.lucidabyss.utils.SiteRoutes
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun SiteHeader(modifier: Modifier = Modifier) {
    Header(
        Modifier
            .tw(
                "fixed top-0 left-1/2 -translate-x-1/2 w-[calc(100%-1rem)] md:w-[calc(100%-2rem)] max-w-[800px] z-50 mt-4",
            ).then(modifier)
            .toAttrs(),
    ) {
        Nav(
            Modifier
                .tw(
                    "flex justify-between items-center px-4 md:px-6 py-3 bg-surface/80 backdrop-blur-md rounded-xl border-none shadow-[0_20px_40px_rgba(42,40,37,0.06)] tonal-contrast-no-borders",
                ).toAttrs(),
        ) {
            NavbarLogo()
            NavbarLinks()
            NavbarActions()
        }
    }
}

@Composable
private fun NavbarLogo() {
    Div({ tw("text-xl font-black text-on-surface font-headline tracking-tight") }) {
        Anchor(href = SiteRoutes.home) {
            Text("tozy")
            Span({ tw("text-primary") }) {
                Text("dev")
            }
        }
    }
}

@Composable
private fun NavbarLinks() {
    Div({ tw("hidden md:flex items-center gap-6 md:absolute md:left-1/2 md:-translate-x-1/2") }) {
        HeaderLink(href = SiteRoutes.home) { Text(Strings.section.header.menu.home) }
        HeaderLink(href = SiteRoutes.blog) { Text(Strings.section.header.menu.blog) }
        HeaderLink(href = SiteRoutes.about) { Text(Strings.section.header.menu.me) }
    }
}

@Composable
private fun HeaderLink(
    href: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val ctx = rememberPageContext()
    val isActive =
        remember(ctx.route.path, href) {
            val homeRoute = SiteRoutes.home
            if (href == homeRoute) {
                ctx.route.path == homeRoute
            } else {
                ctx.route.path.startsWith(href)
            }
        }

    val activeClasses =
        "text-primary font-extrabold border-b-2 border-primary/20 font-headline transition-colors scale-95 active:scale-90"
    val inactiveClasses =
        "text-on-surface-variant font-medium font-headline hover:text-primary transition-colors scale-95 active:scale-90 transition-transform"

    Anchor(
        href = href,
        attrs =
            Modifier
                .tw(if (isActive) activeClasses else inactiveClasses)
                .then(modifier)
                .toAttrs(),
    ) {
        content()
    }
}

@Composable
private fun NavbarActions() {
    Div({ tw("flex items-center gap-4") }) {
        ThemeToggleButton()
    }
}

@Composable
private fun ThemeToggleButton() {
    var themeMode by ThemeMode.currentState
    Button(
        {
            id("theme-toggle")
            tw("text-on-surface scale-95 active:scale-90 transition-transform cursor-pointer")
            onClick { themeMode = themeMode.cycle }
        },
    ) {
        when (themeMode) {
            ThemeMode.Light -> LightModeIcon()
            ThemeMode.Dark -> DarkModeIcon()
            ThemeMode.System -> RoutineIcon()
        }
    }
}
