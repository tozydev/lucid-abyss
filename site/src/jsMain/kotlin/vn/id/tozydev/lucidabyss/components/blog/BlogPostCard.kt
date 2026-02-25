package vn.id.tozydev.lucidabyss.components.blog

import Res
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.dom.GenericTag
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.components.icons.fa.FaCalendar
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.core.BlogPost
import vn.id.tozydev.lucidabyss.utils.coverImagePathOrDefault
import vn.id.tozydev.lucidabyss.utils.formatDate
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun BlogPostCard(
    post: BlogPost,
    modifier: Modifier = Modifier,
) {
    Anchor(
        href = post.route,
        attrs =
            Modifier
                .tw(
                    "card card-border card-lg bg-base-100 hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] hover:-translate-y-1 transition-all duration-300",
                ).then(modifier)
                .toAttrs(),
    ) {
        GenericTag("figure") {
            Img(
                src = post.coverImagePathOrDefault,
                alt = Res.string.widget_post_cover_alt.format(post.title),
            )
            Div({ tw("absolute top-4 right-4") }) {
                Div({ tw("badge") }) {
                    Text(post.topic)
                }
            }
        }
        Div({ tw("card-body") }) {
            GenericTag("time", attrsStr = "datetime=\"${post.publishedAt}\"") {
                FaCalendar(Modifier.tw("mr-1"))
                Text(post.publishedAt.formatDate())
            }
            H3({ tw("card-title") }) { Text(post.title) }
            P { Text(post.description) }
        }
    }
}
