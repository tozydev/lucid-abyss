package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.PointerEvents
import com.varabyte.kobweb.compose.dom.GenericTag
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.graphics.ImageDecoding
import com.varabyte.kobweb.silk.components.graphics.ImageLoading
import com.varabyte.kobweb.silk.components.icons.fa.FaCalendar
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.ComponentKind
import com.varabyte.kobweb.silk.style.CssLayer
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.CssStyleVariant
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.extendedBy
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.models.Post
import vn.id.tozydev.lucidabyss.models.coverImagePathOrDefault
import vn.id.tozydev.lucidabyss.styles.ColorVars
import vn.id.tozydev.lucidabyss.styles.TextSmStyle
import vn.id.tozydev.lucidabyss.styles.TextXlStyle
import vn.id.tozydev.lucidabyss.styles.TextXsStyle
import vn.id.tozydev.lucidabyss.utils.formatDate

sealed interface PostCardKind : ComponentKind

val PostCardStyle =
    CssStyle<PostCardKind>(
        {
            IslandStyle.toModifier(
                ColumnIslandVariant,
                NoPaddingIslandVariant,
                SoftLiftingIslandVariant,
            )
        },
    ) {
        base {
            Modifier.fillMaxSize()
        }
        hover {
            Modifier
                .pointerEvents(PointerEvents.Auto)
                .cursor(Cursor.Pointer)
        }
    }

val PostCardLinkVariant =
    UndecoratedLinkVariant.extendedBy {
        base {
            Modifier.color(ColorVars.TextBody.value())
        }
    }

@CssLayer("component-styles")
val PostCardCoverContainerStyle =
    CssStyle.base {
        Modifier
            .flexShrink(0)
            .height(12.cssRem)
            .position(Position.Relative)
            .overflow(Overflow.Hidden)
    }

@Composable
fun PostCard(
    post: Post,
    modifier: Modifier = Modifier,
    variant: CssStyleVariant<PostCardKind>? = null,
) {
    Link(
        path = post.route,
        modifier = PostCardStyle.toModifier(variant).then(modifier),
        variant = PostCardLinkVariant,
    ) {
        Div(PostCardCoverContainerStyle.toAttrs()) {
            Image(
                post.coverImagePathOrDefault,
                alt = "Cover image for ${post.title}",
                loading = ImageLoading.Lazy,
                decoding = ImageDecoding.Auto,
                modifier =
                    Modifier
                        .fillMaxSize()
                        .position(Position.Absolute)
                        .objectFit(ObjectFit.Cover)
                        .top(0.px)
                        .left(0.px)
                        .right(0.px)
                        .bottom(0.px),
            )
        }
        Column(
            Modifier
                .padding(top = 1.cssRem, leftRight = 1.5.cssRem, bottom = 1.5.cssRem)
                .gap(0.5.cssRem)
                .fillMaxSize(),
        ) {
            Row(
                modifier =
                    TextSmStyle
                        .toModifier()
                        .color(ColorVars.TextLabel.value())
                        .fillMaxWidth()
                        .margin(bottom = 0.5.cssRem),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(0.5.cssRem),
            ) {
                Badge(TextXsStyle.toModifier(), variant = NoneTransformBadgeVariant) {
                    Text(post.topic)
                }
                SpanText("•")
                GenericTag("time", attrsStr = "datetime=\"${post.publishedAt}\"") {
                    FaCalendar()
                    Text(" ${post.publishedAt.formatDate()}")
                }
            }
            H3(
                TextXlStyle
                    .toModifier()
                    .fontWeight(FontWeight.SemiBold)
                    .color(ColorVars.TextHeading.value())
                    .toAttrs(),
            ) {
                Text(post.title)
            }
            P(Modifier.flex(1).toAttrs()) {
                Text(post.description)
            }

            Div(TextSmStyle.toModifier().color(ColorVars.TextLabel.value()).toAttrs()) {
                Text("Đọc thêm")
            }
        }
    }
}
