package vn.id.tozydev.lucidabyss.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaCubes
import com.varabyte.kobweb.silk.components.icons.fa.FaHouse
import com.varabyte.kobweb.silk.components.icons.fa.FaRss
import com.varabyte.kobweb.silk.components.icons.fa.FaUser
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
context(language: SiteLanguage)
fun BottomNavbar(modifier: Modifier = Modifier) {
    val ctx = rememberPageContext()

    @Composable
    fun DockItem(
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

        Button(
            Modifier
                .onClick {
                    ctx.router.navigateTo(path)
                }.toAttrs {
                    if (isActive) {
                        tw("dock-active")
                    }
                },
        ) {
            Span({ tw("size-[1.2em]") }) {
                icon()
            }
            Span({ tw("dock-label") }) {
                Text(label)
            }
        }
    }

    Div(
        Modifier
            .tw("dock dock-xs md:hidden")
            .then(modifier)
            .toAttrs(),
    ) {
        DockItem(SitePaths.home, Res.string.widget_bottom_navbar_home) {
            FaHouse()
        }
        DockItem(SitePaths.about, Res.string.widget_bottom_navbar_me) {
            FaUser()
        }
        DockItem(SitePaths.blog, Res.string.widget_bottom_navbar_blog) {
            FaRss()
        }
        DockItem(SitePaths.products, Res.string.widget_bottom_navbar_products) {
            FaCubes()
        }
    }
}
