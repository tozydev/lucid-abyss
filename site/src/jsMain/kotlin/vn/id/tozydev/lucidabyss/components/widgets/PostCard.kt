package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.PointerEvents
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.graphics.ImageDecoding
import com.varabyte.kobweb.silk.components.graphics.ImageLoading
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.ComponentKind
import com.varabyte.kobweb.silk.style.CssLayer
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.CssStyleVariant
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.elements.Time
import vn.id.tozydev.lucidabyss.models.Post
import vn.id.tozydev.lucidabyss.models.coverImagePathOrDefault
import vn.id.tozydev.lucidabyss.styles.Text2XlStyle
import vn.id.tozydev.lucidabyss.styles.TextSmStyle
import vn.id.tozydev.lucidabyss.utils.formatDate

sealed interface PostCardKind : ComponentKind

val PostCardStyle =
    CssStyle<PostCardKind>(
        {
            IslandStyle.toModifier(ColumnIslandVariant then NoPaddingIslandVariant)
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
    val pageCtx = rememberPageContext()
    Div(
        PostCardStyle
            .toModifier(variant)
            .onClick {
                pageCtx.router.navigateTo(post.route)
            }.then(modifier)
            .toAttrs(),
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
                    Modifier
                        .fillMaxWidth()
                        .gap(0.25.cssRem)
                        .margin(bottom = 0.5.cssRem),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TopicBadge("Test")
                SpanText("•")
                Time(datetime = post.publishedAt.toString(), TextSmStyle.toAttrs()) {
                    Text(post.publishedAt.formatDate())
                }
            }
            H3(
                Text2XlStyle
                    .toModifier()
                    .margin(0.px)
                    .toAttrs(),
            ) {
                Text(post.title)
            }
            P(Modifier.margin(0.px).flex(1).toAttrs()) {
                Text(post.description)
            }

            Div {
                Text("Read more ")
            }
        }
    }
}

val TopicBadgeStyle =
    CssStyle {
        base {
            Modifier
                .backgroundColor(Color.lightgray)
                .color(Color.white)
                .padding(0.25.cssRem, 0.5.cssRem)
                .borderRadius(0.5.cssRem)
                .fontSize(0.75.cssRem)
        }
    }

@Composable
private fun TopicBadge(
    label: String,
    modifier: Modifier = Modifier,
) {
    Span(TopicBadgeStyle.toModifier().then(modifier).toAttrs()) {
        Text(label)
    }
}
