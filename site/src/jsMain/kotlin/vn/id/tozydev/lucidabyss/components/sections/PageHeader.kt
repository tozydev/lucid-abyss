package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.css.autoLength
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.dom.svg.Path
import com.varabyte.kobweb.compose.dom.svg.Svg
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.fa.FaHouse
import com.varabyte.kobweb.silk.components.icons.fa.FaMagnifyingGlass
import com.varabyte.kobweb.silk.components.icons.fa.FaRss
import com.varabyte.kobweb.silk.components.icons.fa.FaUser
import com.varabyte.kobweb.silk.components.icons.fa.IconStyle
import com.varabyte.kobweb.silk.components.layout.VerticalDivider
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.LinkStyle
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.selectors.active
import com.varabyte.kobweb.silk.style.selectors.focusVisible
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.animation.TransitionDurationVars
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.ThemeButton
import vn.id.tozydev.lucidabyss.styles.ColorVars
import vn.id.tozydev.lucidabyss.styles.IconButtonVariant
import vn.id.tozydev.lucidabyss.styles.TextSmStyle
import vn.id.tozydev.lucidabyss.utils.rgb
import vn.id.tozydev.lucidabyss.utils.tw

fun Modifier.zIndexHeader() = this.zIndex(1000)

val PageHeaderStyle =
    CssStyle {
        base {
            Modifier
                .position(Position.Fixed)
                .top(0.px)
                .left(0.px)
                .right(0.px)
                .zIndexHeader()
                .padding(topBottom = 1.5.cssRem, leftRight = 1.cssRem)
                .transition {
                    property("translate")
                    duration(TransitionDurationVars.Slow.value())
                    timingFunction(TransitionTimingFunction.cubicBezier(0.25, 0.8, 0.25, 1.0))
                }
        }

        Breakpoint.MD {
            Modifier
                .left(50.percent)
                .right(autoLength)
                .transform {
                    translateX((-50).percent)
                }
        }
    }

val PageHeaderContainerStyle =
    CssStyle {
        base {
            Modifier
                .fillMaxWidth()
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Row)
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.SpaceBetween)
                .padding(1.cssRem)
                .backgroundColor(rgb(ColorVars.BgSurface, alpha = 0.9f))
                .borderRadius(9999.px)
                .border(1.px, LineStyle.Solid, ColorVars.Outline.value())
                .padding(topBottom = 0.75.cssRem, leftRight = 1.25.cssRem)
                .backdropFilter(blur(40.px))
            // todo drop shadow
        }

        Breakpoint.MD {
            Modifier
                .justifyContent(JustifyContent.Center)
                .gap(0.5.cssRem)
        }
    }

@Composable
fun PageHeader(modifier: Modifier = Modifier) {
    Header(
        Modifier
            .tw(
                "fixed top-0 left-0 right-0 md:left-1/2 md:right-auto",
                "px-4 py-6 md:w-auto",
                "transition-transform duration-300 md:-translate-x-1/2",
            ).zIndexHeader()
            .then(modifier)
            .toAttrs(),
    ) {
        Div(PageHeaderContainerStyle.toModifier().tw("nav").toAttrs()) {
            HeaderLogo()
            HeaderNav()
            VerticalDivider(Modifier.height(1.5.cssRem).displayIfAtLeast(Breakpoint.MD))
            HeaderActions()
        }
    }
}

@Composable
private fun HeaderLogo() {
    Anchor(
        href = "/",
        attrs = Modifier.tw("mr-4").toAttrs(),
    ) {
        Svg(
            Modifier
                .attr("viewBox", "0 0 576 512")
                .tw("inline size-6 mr-1")
                .toAttrs(),
        ) {
            // Font Awesome Free v7.1.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2026 Fonticons, Inc.
            Path {
                d(
                    "M360.8 1.2c-17-4.9-34.7 5-39.6 22l-128 448c-4.9 17 5 34.7 22 39.6s34.7-5 39.6-22l128-448c4.9-17-5-34.7-22-39.6zm64.6 136.1c-12.5 12.5-12.5 32.8 0 45.3l73.4 73.4-73.4 73.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0l96-96c12.5-12.5 12.5-32.8 0-45.3l-96-96c-12.5-12.5-32.8-12.5-45.3 0zm-274.7 0c-12.5-12.5-32.8-12.5-45.3 0l-96 96c-12.5 12.5-12.5 32.8 0 45.3l96 96c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L77.3 256 150.6 182.6c12.5-12.5 12.5-32.8 0-45.3z",
                )
            }
        }
        Span(Modifier.tw("text-sm font-bold").toAttrs()) {
            Text("tozydev")
        }
    }
}

@Composable
private fun HeaderActions() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(0.25.cssRem),
    ) {
        Button(
            {
                window.alert("Search not yet implemented")
            },
            modifier = TextSmStyle.toModifier().size(2.5.cssRem),
            variant = IconButtonVariant,
        ) {
            FaMagnifyingGlass()
        }
        ThemeButton(modifier = TextSmStyle.toModifier().size(2.5.cssRem))
    }
}

val HeaderNavStyle =
    CssStyle {
        base {
            Modifier.display(DisplayStyle.None)
        }
        Breakpoint.MD {
            Modifier
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Row)
                .alignItems(AlignItems.Center)
                .gap(0.25.cssRem)
        }
    }

@Composable
private fun HeaderNav() {
    context(rememberPageContext()) {
        Nav(HeaderNavStyle.toModifier().toAttrs()) {
            NavItem(
                path = "/",
                label = "Trang chủ",
                icon = { FaHouse() },
            )
            NavItem(
                path = "/about",
                label = "Giới thiệu",
                icon = { if (it) FaUser(style = IconStyle.FILLED) else FaUser() },
            )
            NavItem(
                path = "/blog",
                label = "Blog",
                icon = { FaRss() },
            )
        }
    }
}

val NavItemVariant =
    LinkStyle.addVariant({ TextSmStyle.toModifier() }) {
        base {
            Modifier
                .color(ColorVars.TextBody.value())
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Row)
                .alignItems(AlignItems.Center)
                .gap(0.5.cssRem)
                .padding(0.625.cssRem, 1.cssRem)
                .borderRadius(9999.px)
                .fontWeight(FontWeight.Medium)
                .transition {
                    property(TransitionProperty.All)
                    duration(TransitionDurationVars.Fast.value())
                    timingFunction(TransitionTimingFunction.cubicBezier(0.25, 0.8, 0.25, 1.0))
                }.whiteSpace(WhiteSpace.NoWrap)
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

        focusVisible {
            Modifier
                .outline(2.px, LineStyle.Solid, Colors.Transparent)
                .boxShadow(spreadRadius = 0.1875.cssRem, color = ColorVars.FocusRing.value())
        }

        cssRule("[data-active='true']") {
            Modifier
                .color(ColorVars.TextInverse.value())
                .backgroundColor(ColorVars.Primary.value())
                .fontWeight(FontWeight.SemiBold)
        }
    }

@Composable
context(ctx: PageContext)
private fun NavItem(
    path: String,
    label: String,
    icon: @Composable (isActive: Boolean) -> Unit,
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
                    attr("data-active", "true")
                }
            },
        variant = UndecoratedLinkVariant then NavItemVariant,
    ) {
        icon(isActive)
        SpanText(label)
    }
}
