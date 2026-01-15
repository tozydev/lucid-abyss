package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowLeft
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRight
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.models.Post
import vn.id.tozydev.lucidabyss.models.nextPost
import vn.id.tozydev.lucidabyss.models.previousPost
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun NextPrevPosts(
    post: Post,
    modifier: Modifier = Modifier,
) {
    val previousPost = post.previousPost
    val nextPost = post.nextPost
    Div(
        Modifier
            .tw("grid grid-cols-1 md:grid-cols-2 gap-4")
            .then(modifier)
            .toAttrs(),
    ) {
        if (previousPost != null) {
            Anchor(
                href = previousPost.route,
                attrs = { tw("card card-border bg-base-100") },
            ) {
                Div({ tw("card-body") }) {
                    Div({ tw("card-title text-xs uppercase") }) {
                        FaArrowLeft()
                        Text("Bài trước")
                    }
                    H4({ tw("font-semibold text-lg") }) {
                        Text(previousPost.title)
                    }
                }
            }
        }
        if (nextPost != null) {
            Anchor(
                href = nextPost.route,
                attrs = { tw("card card-border bg-base-100 md:col-start-2") },
            ) {
                Div({ tw("card-body items-end") }) {
                    Div({ tw("card-title text-xs uppercase") }) {
                        Text("Bài sau")
                        FaArrowRight()
                    }
                    H4({ tw("font-semibold text-lg") }) {
                        Text(nextPost.title)
                    }
                }
            }
        }
    }
}
