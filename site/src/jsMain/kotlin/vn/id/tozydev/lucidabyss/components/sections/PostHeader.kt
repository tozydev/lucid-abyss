package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.dom.GenericTag
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.graphics.ImageDecoding
import com.varabyte.kobweb.silk.components.graphics.ImageLoading
import com.varabyte.kobweb.silk.components.icons.fa.FaCalendar
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.LinkStyle
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.IslandStyle
import vn.id.tozydev.lucidabyss.components.widgets.NoPaddingIslandVariant
import vn.id.tozydev.lucidabyss.models.Post
import vn.id.tozydev.lucidabyss.models.coverImagePathOrDefault
import vn.id.tozydev.lucidabyss.utils.formatDate
import vn.id.tozydev.lucidabyss.utils.inset

val PostHeaderStyle =
    CssStyle(
        {
            IslandStyle.toModifier(NoPaddingIslandVariant)
        },
    ) {
        base {
            Modifier
                .display(DisplayStyle.Flex)
                .alignItems(AlignItems.FlexEnd)
                .height(32.cssRem)
        }
    }

val PostHeaderContentStyle =
    CssStyle {
        base {
            Modifier
                .zIndex(10)
                .padding(2.cssRem)
                .fillMaxWidth()
                .maxWidth(56.cssRem)
                .color(Colors.White)
        }

        Breakpoint.MD {
            Modifier.padding(3.cssRem)
        }
    }

@Composable
fun PostHeader(
    post: Post,
    modifier: Modifier = Modifier,
) {
    Header(PostHeaderStyle.toModifier().then(modifier).toAttrs()) {
        Image(
            src = post.coverImagePathOrDefault,
            alt = "Cover image for ${post.title}",
            modifier =
                Modifier
                    .position(Position.Absolute)
                    .objectFit(ObjectFit.Cover)
                    .fillMaxSize()
                    .inset(0.px),
            loading = ImageLoading.Lazy,
            decoding = ImageDecoding.Async,
        )

        Div(
            Modifier
                .position(Position.Absolute)
                .inset(0.px)
                .backgroundImage(
                    linearGradient(LinearGradient.Direction.ToTop) {
                        add(Color.rgb(0x111827))
                        add(Color.rgba(17, 24, 39, 0.6f), 50.percent)
                        add(Colors.Transparent)
                    },
                ).toAttrs(),
        )

        Div(PostHeaderContentStyle.toAttrs()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(0.5.cssRem),
            ) {
                TopicBadge()
                SpanText("|")
                GenericTag("time", attrsStr = "datetime=\"${post.publishedAt}\"") {
                    FaCalendar()
                    Text(" ${post.publishedAt.formatDate()}")
                }
            }
            H1 {
                Text(post.title)
            }
            P(
                Modifier
                    .fontWeight(FontWeight.Medium)
                    .toAttrs(),
            ) {
                Text(post.description)
            }
        }
    }
}

val TopicBadgeLinkVariant =
    LinkStyle.addVariant {
        base {
            Modifier
                .textDecorationLine(TextDecorationLine.None)
                .borderRadius(9999.px)
                .background(Colors.LightGray) // todo: use theme color
                .color(Colors.Black) // todo: use theme color
                .padding(0.25.cssRem, 0.75.cssRem)
        }
    }

@Composable
private fun TopicBadge() {
    Link(
        path = "#",
        text = "Kotlin",
        variant = UndecoratedLinkVariant.then(UncoloredLinkVariant).then(TopicBadgeLinkVariant),
    )
}
