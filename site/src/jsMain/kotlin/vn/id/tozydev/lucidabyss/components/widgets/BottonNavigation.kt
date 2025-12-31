package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.autoLength
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaHouse
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.LinkStyle
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

val BottomNavigationStyle =
    CssStyle {
        base {
            Modifier
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Row)
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.SpaceBetween)
                .position(Position.Fixed)
                .bottom(1.cssRem)
                .left(1.cssRem)
                .right(1.cssRem)
                .maxWidth(24.cssRem)
                .margin(leftRight = autoLength)
                .zIndex(1000)
                .borderRadius(9999.px)
                .padding(0.375.cssRem)
                .fillMaxWidth()
                .gap(1.cssRem)
                .border(1.px, LineStyle.Solid, Colors.LightGray) // todo: use theme color
                .color(Colors.Black) // todo: use theme color
                .backgroundColor(Colors.White) // todo: use theme color
        }

        Breakpoint.MD {
            Modifier
                .display(DisplayStyle.None)
        }
    }

@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {
    context(rememberPageContext()) {
        Nav(
            BottomNavigationStyle
                .toModifier()
                .then(modifier)
                .toAttrs(),
        ) {
            NavItem(
                path = "/",
                label = "Home",
            ) {
                FaHouse()
            }
            NavItem(
                path = "/about",
                label = "About",
            ) {
                FaHouse()
            }
            NavItem(
                path = "/blog",
                label = "Blog",
            ) {
                FaHouse()
            }
        }
    }
}

val BottomNavItemLinkVariant =
    LinkStyle.addVariant {
        base {
            Modifier
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Column)
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
                .fillMaxWidth()
                .padding(leftRight = 0.px, topBottom = 0.5.cssRem)
                .textDecorationLine(TextDecorationLine.None)
                .borderRadius(9999.px)
        }
        cssRule("[data-active='true']") {
            Modifier
                .color(Colors.White) // todo: use theme color
                .backgroundColor(Colors.Gray) // todo: use theme color
        }
    }

@Composable
context(ctx: PageContext)
private fun NavItem(
    path: String,
    label: String,
    icon: @Composable () -> Unit,
) {
    val isActive =
        if (path == "/") {
            ctx.route.path == "/"
        } else {
            ctx.route.path.startsWith(path)
        }

    Link(
        path = path,
        modifier =
            Modifier.attrsModifier {
                if (isActive) {
                    this.attr("data-active", "true")
                }
            },
        variant = UndecoratedLinkVariant.then(UncoloredLinkVariant).then(BottomNavItemLinkVariant),
    ) {
        icon()
        Text(label)
    }
}
