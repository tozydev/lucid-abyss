package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.dom.GenericTag
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaCalendar
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.models.Post
import vn.id.tozydev.lucidabyss.models.coverImagePathOrDefault
import vn.id.tozydev.lucidabyss.utils.formatDate
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun PostCard(
    post: Post,
    modifier: Modifier = Modifier,
) {
    A(
        href = post.route,
        attrs =
            Modifier
                .tw("card card-border card-lg bg-base-100")
                .then(modifier)
                .toAttrs(),
    ) {
        GenericTag("figure") {
            Img(
                src = post.coverImagePathOrDefault,
                alt = "Cover image for ${post.title}",
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
