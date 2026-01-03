package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.Island
import vn.id.tozydev.lucidabyss.components.widgets.IslandStyle
import vn.id.tozydev.lucidabyss.models.Constants

val PageFooterStyle =
    CssStyle {
        base {
            Modifier
                .fillMaxWidth()
                .padding(bottom = 3.cssRem, leftRight = 1.cssRem)
        }
    }

val PageFooterIslandVariant =
    IslandStyle.addVariant {
        base {
            Modifier
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Column)
                .justifyContent(JustifyContent.Center)
                .alignItems(AlignItems.Center)
                .padding(topBottom = 1.5.cssRem, leftRight = 2.cssRem)
        }
        Breakpoint.MD {
            Modifier
                .flexDirection(FlexDirection.Row)
                .justifyContent(JustifyContent.SpaceBetween)
        }
    }

@Composable
fun PageFooter(modifier: Modifier = Modifier) {
    Footer(
        PageFooterStyle
            .toModifier()
            .then(modifier)
            .toAttrs(),
    ) {
        Island(variant = PageFooterIslandVariant) {
            Row(
                modifier = Modifier.flexGrow(1),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SpanText("© 2025 tozydev. Built with ")
                Link(Constants.KOBWEB_URL, "Kobweb")
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(1.cssRem),
            ) {
                Link(Constants.RSS_URL, "RSS", openInternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB)
                Link(Constants.GITHUB_URL, "GitHub")
                Link(Constants.EMAIL_URL, "Email")
            }
        }
    }
}
