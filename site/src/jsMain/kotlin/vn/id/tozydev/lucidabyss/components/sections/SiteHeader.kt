package vn.id.tozydev.lucidabyss.components.sections

import Res
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
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
import kotlinx.browser.document
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement
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
context(language: SiteLanguage)
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

    @Composable
    fun LanguageDropdown() {
        val ctx = rememberPageContext()
        Div({ tw("dropdown dropdown-end md:dropdown-center") }) {
            Div(
                {
                    tw("btn btn-sm btn-ghost btn-circle")
                    attr("tabindex", "0")
                    attr("role", "button")
                },
            ) {
                Span({ tw("text-xs font-bold") }) {
                    Text(language.code.uppercase())
                }
            }
            Ul(
                {
                    tw("dropdown-content menu menu-sm bg-base-100 rounded-box z-1 mt-3 w-52 p-2 shadow")
                    attr("tabindex", "0")
                },
            ) {
                SiteLanguage.entries.forEach { lang ->
                    Li {
                        val isCurrent = lang == language
                        val targetPath =
                            if (isCurrent) {
                                ctx.route.path
                            } else {
                                getLanguageCounterpartPath(ctx.route.path, language)
                            }

                        Anchor(
                            href = targetPath,
                            attrs =
                                Modifier
                                    .thenIf(isCurrent, Modifier.tw("menu-active"))
                                    .onClick { document.activeElement?.unsafeCast<HTMLElement>()?.blur() }
                                    .toAttrs(),
                        ) {
                            Text(lang.label)
                        }
                    }
                }
            }
        }
    }

    Div({ tw("flex justify-center gap-2") }) {
        Button({ tw("btn btn-sm btn-ghost btn-circle") }) {
            FaMagnifyingGlass()
        }
        LanguageDropdown()
        ThemeModeButton()
    }
}

private fun getLanguageCounterpartPath(
    path: String,
    language: SiteLanguage,
): String {
    val enPrefix = "/${SiteLanguage.En.code}"
    return if (language == SiteLanguage.Vi) {
        when (path) {
            SitePaths.HOME_PATH -> enPrefix
            SitePaths.ABOUT_PATH -> "$enPrefix${SitePaths.ABOUT_PATH}"
            SitePaths.BLOG_PATH -> "$enPrefix${SitePaths.BLOG_PATH}"
            SitePaths.PRODUCTS_VI_PATH -> "$enPrefix${SitePaths.PRODUCTS_EN_PATH}"
            else -> "$enPrefix$path"
        }
    } else {
        val pathWithoutEn = path.removePrefix(enPrefix).takeIf { it.isNotEmpty() } ?: "/"
        when (pathWithoutEn) {
            SitePaths.ABOUT_PATH -> SitePaths.ABOUT_PATH
            SitePaths.BLOG_PATH -> SitePaths.BLOG_PATH
            SitePaths.PRODUCTS_EN_PATH -> SitePaths.PRODUCTS_VI_PATH
            else -> pathWithoutEn
        }
    }
}
