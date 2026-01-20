package vn.id.tozydev.lucidabyss.components.sections

import Res
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.components.icons.fa.FaCubes
import com.varabyte.kobweb.silk.components.icons.fa.FaDesktop
import com.varabyte.kobweb.silk.components.icons.fa.FaHouse
import com.varabyte.kobweb.silk.components.icons.fa.FaMagnifyingGlass
import com.varabyte.kobweb.silk.components.icons.fa.FaMoon
import com.varabyte.kobweb.silk.components.icons.fa.FaRss
import com.varabyte.kobweb.silk.components.icons.fa.FaSun
import com.varabyte.kobweb.silk.components.icons.fa.FaUser
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths
import vn.id.tozydev.lucidabyss.styles.ThemeMode
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
context(language: SiteLanguage)
fun SiteHeader(modifier: Modifier = Modifier) {
    Header(
        Modifier
            .tw("fixed top-0 left-0 right-0 z-50 p-4 md:left-1/2 md:right-auto md:-translate-x-1/2 md:w-auto")
            .then(modifier)
            .toAttrs(),
    ) {
        Nav({ tw("navbar glass justify-between rounded-full md:w-max") }) {
            HeaderLogo()
            HeaderMenu()
            HeaderActions()
        }
    }
}

@Composable
context(language: SiteLanguage)
private fun HeaderLogo() {
    Div({ }) {
        Anchor(
            href = SitePaths.home,
            { tw("btn btn-ghost btn-sm rounded-full") },
        ) {
            Text("tozydev")
        }
    }
}

@Composable
context(language: SiteLanguage)
private fun HeaderMenu() {
    val ctx = rememberPageContext()

    @Composable
    fun MenuItem(
        path: String,
        label: String,
        icon: @Composable () -> Unit,
    ) {
        val isActive =
            run {
                val homeRoute = SitePaths.home
                if (path == homeRoute) {
                    ctx.route.path == homeRoute
                } else {
                    ctx.route.path.startsWith(path)
                }
            }

        Li(
            Modifier.toAttrs {
                if (isActive) {
                    tw("menu-active")
                }
            },
        ) {
            Anchor(href = path) {
                icon()
                Span({ tw("not-sm:hidden") }) {
                    Text(label)
                }
            }
        }
    }

    Div({ tw("hidden items-center md:flex") }) {
        Ul({ tw("menu menu-horizontal font-medium gap-2") }) {
            MenuItem(SitePaths.home, Res.string.section_header_menu_home) {
                FaHouse()
            }
            MenuItem(SitePaths.about, Res.string.section_header_menu_me) {
                FaUser()
            }
            MenuItem(SitePaths.blog, Res.string.section_header_menu_blog) {
                FaRss()
            }
            MenuItem(SitePaths.products, Res.string.section_header_menu_products) {
                FaCubes()
            }
        }
    }
}

@Composable
private fun HeaderActions() {
    @Composable
    fun ThemeModeButton() {
        var themeMode by ThemeMode.currentState
        Button(
            {
                tw("btn btn-sm btn-ghost btn-circle")
                onClick { themeMode = themeMode.cycle }
            },
        ) {
            when (themeMode.cycle) {
                ThemeMode.Light -> FaSun()
                ThemeMode.Dark -> FaMoon()
                ThemeMode.System -> FaDesktop()
            }
        }
    }

    Div({ tw("flex justify-center gap-2") }) {
        Button({ tw("btn btn-sm btn-ghost btn-circle") }) {
            FaMagnifyingGlass()
        }
        ThemeModeButton()
    }
}
