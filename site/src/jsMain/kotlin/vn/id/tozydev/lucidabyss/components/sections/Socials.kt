package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaDiscord
import com.varabyte.kobweb.silk.components.icons.fa.FaEnvelope
import com.varabyte.kobweb.silk.components.icons.fa.FaGithub
import com.varabyte.kobweb.silk.components.icons.fa.FaLinkedin
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.ColumnIslandVariant
import vn.id.tozydev.lucidabyss.components.widgets.IslandStyle

val SocialsStyle =
    CssStyle {
        base {
            Modifier
                .display(DisplayStyle.Grid)
                .gridTemplateColumns { repeat(2) { minmax(0.px, 1.fr) } }
                .gap(1.cssRem)
        }
        Breakpoint.MD {
            Modifier
                .gridTemplateColumns { repeat(4) { minmax(0.px, 1.fr) } }
        }
    }

@Composable
fun Socials(modifier: Modifier = Modifier) {
    Div(SocialsStyle.toModifier().then(modifier).toAttrs()) {
        SocialLink(
            path = "#",
            icon = { FaGithub() },
            label = "GitHub",
        )
        SocialLink(
            path = "#",
            icon = { FaLinkedin() },
            label = "LinkedIn",
        )
        SocialLink(
            path = "#",
            icon = { FaDiscord() },
            label = "Discord",
        )
        SocialLink(
            path = "#",
            icon = { FaEnvelope() },
            label = "Email",
        )
    }
}

val SocialLinkStyle =
    CssStyle {
        base {
            Modifier
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
                .textDecorationLine(TextDecorationLine.None)
        }
    }

@Composable
fun SocialLink(
    path: String,
    icon: @Composable () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    Link(
        path = path,
        modifier =
            IslandStyle
                .toModifier(ColumnIslandVariant)
                .then(SocialLinkStyle.toModifier())
                .then(modifier),
        variant = UndecoratedLinkVariant.then(UncoloredLinkVariant),
    ) {
        icon()
        SpanText(label)
    }
}
