package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.SiteRoutes
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun BottomNavbar(modifier: Modifier = Modifier) {
    Nav(
        Modifier
            .tw(
                "fixed bottom-0 left-0 right-0 w-full z-50 flex justify-around items-center px-6 pt-2 pb-[env(safe-area-inset-bottom)] bg-surface/95 backdrop-blur-md border-t border-outline/10",
            ).then(modifier)
            .toAttrs(),
    ) {
        BottomNavItem(
            href = SiteRoutes.home,
            label = Strings.commons.navigation.home,
            icon = { isActive ->
                if (isActive) {
                    HomeFilledIcon()
                } else {
                    HomeIcon()
                }
            },
        )
        BottomNavItem(
            href = SiteRoutes.blog,
            label = Strings.commons.navigation.blog,
            icon = { isActive ->
                if (isActive) {
                    ArticleFilledIcon()
                } else {
                    ArticleIcon()
                }
            },
        )
        BottomNavItem(
            href = SiteRoutes.about,
            label = Strings.commons.navigation.about,
            icon = { isActive ->
                if (isActive) {
                    PersonFilledIcon()
                } else {
                    PersonIcon()
                }
            },
        )
    }
}

@Composable
private fun BottomNavItem(
    href: String,
    label: String,
    icon: @Composable (isActive: Boolean) -> Unit,
    modifier: Modifier = Modifier,
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

    Anchor(
        href = href,
        attrs =
            Modifier
                .tw("flex flex-col items-center justify-center gap-0.5 scale-95 active:scale-90 transition-transform group")
                .then(modifier)
                .toAttrs(),
    ) {
        Span(
            {
                tw("rounded-full px-4 py-0.5 transition-colors")
                if (isActive) {
                    tw("bg-primary-container text-on-primary-container")
                } else {
                    tw("text-on-surface-variant group-hover:bg-surface-container")
                }
            },
        ) {
            icon(isActive)
        }
        Span(
            {
                tw("text-[10px] font-label font-bold")
                if (isActive) tw("text-primary") else tw("text-on-surface-variant")
            },
        ) {
            Text(label)
        }
    }
}
