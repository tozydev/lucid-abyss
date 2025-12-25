package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.graphics.ImageLoading
import com.varabyte.kobweb.silk.components.icons.mdi.IconStyle
import com.varabyte.kobweb.silk.components.icons.mdi.MdiArticle
import com.varabyte.kobweb.silk.components.icons.mdi.MdiHome
import com.varabyte.kobweb.silk.components.icons.mdi.MdiPerson
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.LinkStyle
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.selectors.link
import com.varabyte.kobweb.silk.style.selectors.visited
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.color.BorderColorVar
import org.jetbrains.compose.web.css.*
import vn.id.tozydev.lucidabyss.components.widgets.Container
import vn.id.tozydev.lucidabyss.components.widgets.ThemeButton
import vn.id.tozydev.lucidabyss.models.Constants.EMAIL_HASH
import vn.id.tozydev.lucidabyss.theme.toColorScheme
import vn.id.tozydev.lucidabyss.utils.getGravatarUrl

val NavigationStyle =
    CssStyle {
        base {
            Modifier
                .position(Position.Sticky)
                .top(1.cssRem)
                .height(100.vh - 2.cssRem)
                .padding(topBottom = 2.cssRem, leftRight = 1.cssRem)
        }
    }

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    Container(
        modifier =
            NavigationStyle
                .toModifier()
                .then(modifier),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().gap(2.cssRem),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column {
                NavigationHeader()
            }
            Column(
                modifier =
                    Modifier
                        .gap(1.5.cssRem)
                        .flexGrow(1),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                NavigationContent()
            }
            Column {
                NavigationFooter()
            }
        }
    }
}

@Composable
private fun NavigationHeader() {
    Link(
        path = "/",
        variant = UndecoratedLinkVariant.then(UncoloredLinkVariant),
    ) {
        Image(
            src = getGravatarUrl(EMAIL_HASH, size = 64),
            alt = "Logo",
            loading = ImageLoading.Eager,
            modifier =
                Modifier
                    .size(64.px)
                    .borderRadius(50.percent)
                    .border(1.px, LineStyle.Solid, BorderColorVar.value()),
        )
    }
}

@Composable
private fun NavigationFooter() {
    ThemeButton()
}

@Composable
private fun NavigationContent() {
    val ctx = rememberPageContext()
    context(ctx) {
        NavigationItem("/") { isActive ->
            if (isActive) {
                MdiHome()
            } else {
                MdiHome(style = IconStyle.OUTLINED)
            }
        }
        NavigationItem("/about") { isActive ->
            if (isActive) {
                MdiPerson()
            } else {
                MdiPerson(style = IconStyle.OUTLINED)
            }
        }
        NavigationItem("/posts/") { isActive ->
            if (isActive) {
                MdiArticle()
            } else {
                MdiArticle(style = IconStyle.OUTLINED)
            }
        }
    }
}

val NavigationItemLinkVariant =
    LinkStyle.addVariant {
        val colorScheme = colorMode.toColorScheme()
        val colorModifier = Modifier.color(colorScheme.onSurfaceVariant)
        base {
            Modifier
                .size(48.px)
                .display(DisplayStyle.LegacyInlineFlex)
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
                .borderRadius(50.percent)
        }
        hover {
            Modifier
                .textDecorationLine(TextDecorationLine.None)
                .border(1.px, LineStyle.Solid, colorScheme.outline)
                .backgroundColor(colorScheme.surfaceContainerHighest)
                .fontWeight(FontWeight.Bolder)
        }

        link {
            colorModifier
        }

        visited {
            colorModifier
        }

        cssRule("[data-active='true']") {
            Modifier
                .color(colorScheme.onSecondaryContainer)
                .backgroundColor(colorScheme.secondaryContainer)
                .border(1.px, LineStyle.Solid, colorScheme.outline)
        }
    }

@Composable
context(ctx: PageContext)
private fun NavigationItem(
    path: String,
    content: @Composable (isActive: Boolean) -> Unit,
) {
    val isActive =
        if (path == "/") {
            ctx.route.path == "/"
        } else {
            ctx.route.path.startsWith(path)
        }

    Link(
        path = path,
        variant = NavigationItemLinkVariant,
        modifier =
            Modifier.attrsModifier {
                if (isActive) {
                    attr("data-active", "true")
                }
            },
    ) {
        content(isActive)
    }
}
