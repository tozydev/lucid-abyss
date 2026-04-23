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
import vn.id.tozydev.lucidabyss.components.widgets.SearchIcon
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.styles.ThemeMode
import vn.id.tozydev.lucidabyss.utils.SiteRoutes
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun SiteHeader(
    modifier: Modifier = Modifier,
    onOpenCommandOverlay: () -> Unit = {},
) {
    Header(
        Modifier
            .tw("fixed top-4 inset-x-2 md:inset-x-4 max-w-200 mx-auto z-50 select-none")
            .then(modifier)
            .toAttrs(),
    ) {
        Nav(
            {
                tw(
                    "flex justify-between items-center px-4 md:px-6 py-3 bg-surface/80 backdrop-blur-md rounded-xl border border-outline/10 antialiased shadow-soft",
                )
            },
        ) {
            Div({ tw("flex-1") }) {
                NavbarLogo()
            }

            NavbarLinks()

            Div({ tw("flex-1 flex justify-end") }) {
                NavbarActions(onOpenCommandOverlay = onOpenCommandOverlay)
            }
        }
    }
}

@Composable
private fun NavbarLogo() {
    Div({ tw("text-xl font-extrabold text-on-surface font-headline hover:opacity-80 transition-opacity") }) {
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
    Div({ tw("hidden md:flex flex-none items-center") }) {
        Ul({ tw("flex items-center gap-6") }) {
            HeaderLink(href = SiteRoutes.home) { Text(Strings.commons.navigation.home) }
            HeaderLink(href = SiteRoutes.blog) { Text(Strings.commons.navigation.blog) }
            HeaderLink(href = SiteRoutes.about) { Text(Strings.commons.navigation.about) }
        }
    }
}

@Composable
private fun HeaderLink(
    href: String,
    content: @Composable () -> Unit,
) {
    val ctx = rememberPageContext()
    val isActive =
        remember(ctx.route.path, href) {
            if (href == SiteRoutes.home) {
                ctx.route.path == href
            } else {
                ctx.route.path.startsWith(href)
            }
        }

    Li(
        Modifier
            .toAttrs {
                tw("border-b-2 border-primary/20 font-label scale-95 active:scale-90 transition")
                if (isActive) {
                    tw("text-primary font-extrabold")
                } else {
                    tw("text-on-surface-variant font-medium hover:text-primary")
                }
            },
    ) {
        Anchor(href = href) {
            content()
        }
    }
}

@Composable
private fun NavbarActions(onOpenCommandOverlay: () -> Unit) {
    Div({ tw("flex items-center gap-4") }) {
        CommandOverlayButton(onClick = onOpenCommandOverlay)
        ThemeToggleButton()
    }
}

@Composable
private fun CommandOverlayButton(onClick: () -> Unit) {
    Button(
        {
            id("command-overlay-toggle")
            tw("text-on-surface scale-95 active:scale-90 transition-transform cursor-pointer")
            attr("aria-label", Strings.commons.actions.openCommandPalette)
            onClick { onClick() }
        },
    ) {
        SearchIcon()
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
