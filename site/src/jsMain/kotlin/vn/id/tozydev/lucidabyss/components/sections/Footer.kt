package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.icons.mdi.MdiEmail
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.LinkStyle
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.selectors.hover
import org.jetbrains.compose.web.css.*
import vn.id.tozydev.lucidabyss.components.widgets.Container
import vn.id.tozydev.lucidabyss.components.widgets.GitHubIcon
import vn.id.tozydev.lucidabyss.models.Constants
import vn.id.tozydev.lucidabyss.theme.toColorScheme

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Container(modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.flexGrow(1),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SpanText("© 2025 tozydev. Built with ")
                Link(Constants.KOBWEB_URL, "Kobweb")
            }
            Row(
                modifier = Modifier.gap(1.cssRem),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconLink(Constants.EMAIL_URL) {
                    MdiEmail()
                }
                IconLink(Constants.GITHUB_URL) {
                    GitHubIcon()
                }
            }
        }
    }
}

val IconLinkVariant =
    LinkStyle.addVariant {
        val colorScheme = colorMode.toColorScheme()
        base {
            Modifier
                .color(colorScheme.onSurfaceVariant)
                .borderRadius(50.percent)
                .padding(0.5.cssRem)
                .backgroundColor(Colors.Transparent)
                .transition(Transition.of("background-color", 0.2.s), Transition.of("color", 0.2.s))
                .display(DisplayStyle.LegacyInlineFlex)
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
        }
        hover {
            Modifier
                .textDecorationLine(TextDecorationLine.None)
                .backgroundColor(colorScheme.surfaceContainerHighest)
                .color(colorScheme.primary)
        }
    }

@Composable
private fun IconLink(
    url: String,
    content: @Composable () -> Unit,
) {
    Link(path = url, variant = IconLinkVariant) {
        content()
    }
}
