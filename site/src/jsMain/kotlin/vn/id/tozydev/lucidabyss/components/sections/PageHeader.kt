package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.autoLength
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
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.fa.FaBlog
import com.varabyte.kobweb.silk.components.icons.fa.FaHouse
import com.varabyte.kobweb.silk.components.icons.fa.FaMagnifyingGlass
import com.varabyte.kobweb.silk.components.icons.fa.FaSun
import com.varabyte.kobweb.silk.components.icons.fa.FaUser
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.LinkStyle
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.styles.TextSmStyle

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
                .backgroundColor(Colors.White) // todo: use theme color
                .borderRadius(9999.px)
                .border(1.px, LineStyle.Solid, Colors.LightGray) // todo: use theme color
                .padding(topBottom = 0.75.cssRem, leftRight = 1.25.cssRem)
        }

        Breakpoint.MD {
            Modifier
                .justifyContent(JustifyContent.Center)
                .gap(1.cssRem)
        }
    }

@Composable
fun PageHeader(modifier: Modifier = Modifier) {
    Header(PageHeaderStyle.toModifier().then(modifier).toAttrs()) {
        Div(PageHeaderContainerStyle.toAttrs()) {
            HeaderLogo()
            HeaderNav()
            HeaderActions()
        }
    }
}

@Composable
private fun HeaderLogo() {
    Link("/") {
        SpanText(
            "T",
            modifier =
                Modifier
                    .fontSize(1.5.cssRem)
                    .fontWeight(FontWeight.Bold)
                    .textAlign(TextAlign.Center)
                    .color(Colors.Black)
                    .size(2.5.cssRem)
                    .borderRadius(50.percent)
                    .border(2.px, LineStyle.Solid, Colors.Black)
                    .padding(0.25.cssRem)
                    .margin(right = 0.5.cssRem),
        ) // todo: use actual logo
        SpanText("tozydev", modifier = TextSmStyle.toModifier().fontWeight(FontWeight.SemiBold))
    }
}

@Composable
private fun HeaderActions() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(0.5.cssRem),
    ) {
        Button(
            {
            },
        ) {
            FaMagnifyingGlass()
        }
        Button(
            {
            },
        ) {
            FaSun()
        }
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
                label = "Home",
                icon = { FaHouse() },
            )
            NavItem(
                path = "/about",
                label = "About",
                icon = { FaUser() },
            )
            NavItem(
                path = "/blog",
                label = "Blog",
                icon = { FaBlog() },
            )
        }
    }
}

val NavItemVariant =
    LinkStyle.addVariant({ TextSmStyle.toModifier() }) {
        base {
            Modifier
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Row)
                .alignItems(AlignItems.Center)
                .gap(0.5.cssRem)
                .padding(0.625.cssRem, 1.cssRem)
                .borderRadius(9999.px)
                .fontWeight(FontWeight.Medium)
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
                    attr("data-active", "true")
                }
            },
        variant = NavItemVariant,
    ) {
        icon()
        SpanText(label)
    }
}
