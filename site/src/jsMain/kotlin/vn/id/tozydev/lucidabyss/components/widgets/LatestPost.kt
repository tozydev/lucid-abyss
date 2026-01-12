package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.dom.GenericTag
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRightLong
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement
import vn.id.tozydev.lucidabyss.models.Post
import vn.id.tozydev.lucidabyss.models.coverImagePathOrDefault
import vn.id.tozydev.lucidabyss.models.publishedAtFormatted
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun LatestPost(
    post: Post,
    modifier: Modifier = Modifier,
) {
    Div(
        Modifier
            .tw("card image-full card-lg")
            .then(modifier)
            .toAttrs(),
    ) {
        GenericTag("figure") {
            Img(
                src = post.coverImagePathOrDefault,
                alt = "Cover image for ${post.title}",
            )
        }

        Div({ tw("card-body") }) {
            Div({ tw("flex justify-between items-center") }) {
                Div({ tw("badge badge-accent") }) {
                    Text("Mới nhất")
                }
                GenericTag<HTMLElement>(
                    name = "time",
                    attrs = {
                        tw("text-sm")
                        attr("datetime", post.publishedAt.toString())
                    },
                ) {
                    Text(post.publishedAtFormatted)
                }
            }

            H3({ tw("card-title") }) { Text(post.title) }
            P { Text(post.description) }
            Div({ tw("card-actions") }) {
                Anchor(
                    href = post.route,
                ) {
                    Text("Đọc thêm")
                    FaArrowRightLong(Modifier.tw("ml-2"))
                }
            }
        }
    }
}
