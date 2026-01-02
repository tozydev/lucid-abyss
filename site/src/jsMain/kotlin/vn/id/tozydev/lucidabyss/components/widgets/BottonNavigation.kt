package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.css.autoLength
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaHouse
import com.varabyte.kobweb.silk.components.icons.fa.FaRss
import com.varabyte.kobweb.silk.components.icons.fa.FaUser
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.LinkStyle
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.selectors.active
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.animation.TransitionDurationVars
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.styles.ColorVars
import vn.id.tozydev.lucidabyss.styles.TextSmStyle
import vn.id.tozydev.lucidabyss.utils.rgb

fun Modifier.zIndexBottomNav() = this.zIndex(1000)

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
                .zIndexBottomNav()
                .borderRadius(9999.px)
                .padding(0.375.cssRem)
                .border(1.px, LineStyle.Solid, ColorVars.Outline.value())
                .color(ColorVars.TextBody.value())
                .backgroundColor(rgb(ColorVars.BgSurface, alpha = 0.9f))
                .transition {
                    property(TransitionProperty.All)
                    duration(TransitionDurationVars.Fast.value())
                    timingFunction(TransitionTimingFunction.cubicBezier(0.25, 0.8, 0.25, 1.0))
                }.backdropFilter(blur(40.px))
        }

        cssRule(CSSMediaQuery.MediaFeature("max-width", 400.px)) {
            Modifier
                .left(0.5.cssRem)
                .right(0.5.cssRem)
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
                label = "Tranh chủ",
            ) {
                FaHouse()
            }
            NavItem(
                path = "/about",
                label = "Giới thiệu",
            ) {
                FaUser()
            }
            NavItem(
                path = "/blog",
                label = "Blog",
            ) {
                FaRss()
            }
        }
    }
}

val BottomNavItemLinkVariant =
    LinkStyle.addVariant({ TextSmStyle.toModifier() }) {
        base {
            Modifier
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Column)
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
                .fillMaxWidth()
                .padding(leftRight = 0.px, topBottom = 0.5.cssRem)
                .textDecorationLine(TextDecorationLine.None)
                .gap(0.25.cssRem)
                .borderRadius(9999.px)
                .transition {
                    property(TransitionProperty.All)
                    duration(TransitionDurationVars.Fast.value())
                    timingFunction(TransitionTimingFunction.cubicBezier(0.25, 0.8, 0.25, 1.0))
                }
        }

        hover {
            Modifier
                .scale(1.05f)
                .backgroundColor(ColorVars.StateHover.value())
        }

        active {
            Modifier
                .scale(1.05f)
                .backgroundColor(ColorVars.StatePressed.value())
        }

        cssRule("[data-active='true']") {
            Modifier
                .color(ColorVars.TextInverse.value())
                .backgroundColor(ColorVars.Primary.value())
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
