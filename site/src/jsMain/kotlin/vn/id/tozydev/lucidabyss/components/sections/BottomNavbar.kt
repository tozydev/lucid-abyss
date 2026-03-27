package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.MaterialSymbol
import vn.id.tozydev.lucidabyss.core.SiteRoutes
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun BottomNavbar(modifier: Modifier = Modifier) {
    Nav(
        Modifier
            .tw(
                "md:hidden fixed bottom-0 left-0 right-0 w-full z-50 flex justify-around items-center px-6 py-2 bg-surface/95 backdrop-blur-md border-t border-surface-variant/30 shadow-[0_-10px_40px_rgba(42,40,37,0.06)] pb-[env(safe-area-inset-bottom)]",
            ).then(modifier)
            .toAttrs(),
    ) {
        BottomNavItem(
            href = SiteRoutes.home,
            label = Strings.widget.bottomNavbar.home,
            icon = "home",
        )
        BottomNavItem(
            href = SiteRoutes.blog,
            label = Strings.widget.bottomNavbar.blog,
            icon = "article",
        )
        BottomNavItem(
            href = SiteRoutes.projects,
            label = Strings.widget.bottomNavbar.projects,
            icon = "explore",
        )
        BottomNavItem(
            href = SiteRoutes.about,
            label = Strings.widget.bottomNavbar.me,
            icon = "person",
        )
    }
}

@Composable
private fun BottomNavItem(
    href: String,
    label: String,
    icon: String,
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
        MaterialSymbol(
            icon = icon,
            modifier =
                Modifier.tw(
                    "text-2xl rounded-full px-4 py-0.5 transition-colors ${if (isActive) "bg-primary-container text-on-primary-container" else "text-on-surface-variant group-hover:bg-surface-container"}",
                ),
        )
        Span(
            Modifier
                .tw("text-[10px] font-headline font-bold ${if (isActive) "text-primary" else "text-on-surface-variant"}")
                .toAttrs(),
        ) {
            Text(label)
        }
    }
}
