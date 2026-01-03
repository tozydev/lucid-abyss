package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextTransform
import com.varabyte.kobweb.compose.dom.GenericTag
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRightLong
import com.varabyte.kobweb.silk.components.icons.fa.FaPenNib
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.extendedBy
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement
import vn.id.tozydev.lucidabyss.models.Post
import vn.id.tozydev.lucidabyss.models.publishedAtFormatted
import vn.id.tozydev.lucidabyss.styles.ColorVars
import vn.id.tozydev.lucidabyss.styles.TextLgStyle
import vn.id.tozydev.lucidabyss.styles.TextSmStyle

val LatestPostHeaderStyle =
    TextSmStyle.extendedBy {
        base {
            Modifier
                .fontWeight(FontWeight.Medium)
                .margin(bottom = 0.5.cssRem)
                .color(ColorVars.TextLabel.value())
        }
    }

@Composable
fun LatestPost(
    post: Post,
    modifier: Modifier = Modifier,
) {
    Island(
        modifier = modifier,
        variant = ColumnIslandVariant,
    ) {
        Row(
            modifier = LatestPostHeaderStyle.toModifier(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Span(
                Modifier
                    .textTransform(TextTransform.Uppercase)
                    .toAttrs(),
            ) {
                FaPenNib(Modifier.margin(right = 0.5.cssRem))
                Text("Bài viết mới nhất")
            }
            GenericTag<HTMLElement>(
                name = "time",
                attrs =
                    TextSmStyle.toAttrs {
                        attr("datetime", post.publishedAt.toString())
                    },
            ) {
                Text(post.publishedAtFormatted)
            }
        }
        Column(Modifier.fillMaxSize()) {
            H3(
                TextLgStyle
                    .toModifier()
                    .color(ColorVars.TextHeading.value())
                    .fontWeight(FontWeight.SemiBold)
                    .margin(bottom = 0.5.cssRem)
                    .toAttrs(),
            ) {
                Text(post.title)
            }
            P(Modifier.flex(1).toAttrs()) {
                Text(post.description)
            }
            Link(
                post.route,
                modifier = Modifier.margin(top = 1.cssRem),
                variant = UndecoratedLinkVariant,
            ) {
                SpanText("Đọc tiếp", Modifier.margin(right = 0.5.cssRem))
                FaArrowRightLong()
            }
        }
    }
}
