package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
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
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.animation.TransitionDurationVars
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.Badge
import vn.id.tozydev.lucidabyss.components.widgets.IslandStyle
import vn.id.tozydev.lucidabyss.components.widgets.NoPaddingIslandVariant
import vn.id.tozydev.lucidabyss.components.widgets.NoneTransformBadgeVariant
import vn.id.tozydev.lucidabyss.models.Post
import vn.id.tozydev.lucidabyss.models.coverImagePathOrDefault
import vn.id.tozydev.lucidabyss.styles.Text4XlStyle
import vn.id.tozydev.lucidabyss.styles.TextLgStyle
import vn.id.tozydev.lucidabyss.styles.TextSmStyle
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
        cssRule(":hover img") {
            Modifier.scale(1.05)
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
                    .inset(0.px)
                    .transition {
                        property(TransitionProperty.All)
                        duration(TransitionDurationVars.UltraSlow.value())
                        timingFunction(TransitionTimingFunction.cubicBezier(0.25, 0.8, 0.25, 1.0))
                    },
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
                modifier = TextSmStyle.toModifier().margin(bottom = 1.cssRem),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(0.5.cssRem),
            ) {
                Badge(variant = NoneTransformBadgeVariant) {
                    Text(post.topic)
                }
                SpanText("|")
                GenericTag("time", attrsStr = "datetime=\"${post.publishedAt}\"") {
                    FaCalendar()
                    Text(" ${post.publishedAt.formatDate()}")
                }
            }
            H1(
                Text4XlStyle
                    .toModifier()
                    .fontWeight(FontWeight.Bold)
                    .margin(bottom = 0.5.cssRem)
                    .toAttrs(),
            ) {
                Text(post.title)
            }
            P(
                TextLgStyle
                    .toModifier()
                    .fontWeight(FontWeight.Medium)
                    .toAttrs(),
            ) {
                Text(post.description)
            }
        }
    }
}
