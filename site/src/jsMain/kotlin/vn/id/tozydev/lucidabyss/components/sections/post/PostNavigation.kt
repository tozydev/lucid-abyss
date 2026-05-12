package vn.id.tozydev.lucidabyss.components.sections.post

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.generated.Post
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.nextPost
import vn.id.tozydev.lucidabyss.utils.previousPost
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun PostNavigation(
    post: Post,
    modifier: Modifier = Modifier,
) {
    val previousPost = post.previousPost
    val nextPost = post.nextPost
    if (previousPost == null && nextPost == null) return

    Nav(
        Modifier
            .tw("grid grid-cols-1 md:grid-cols-2 gap-4")
            .then(modifier)
            .toAttrs(),
    ) {
        if (previousPost != null) {
            Anchor(
                href = previousPost.route,
                attrs = {
                    tw(
                        "surface-island surface-island-interactive flex flex-col gap-1 p-5 md:p-6 group text-left",
                    )
                },
            ) {
                Span({ tw("font-label text-xs uppercase tracking-wider text-outline group-hover:text-primary transition-colors") }) {
                    Text(Strings.commons.actions.prevPost)
                }
                Span({ tw("font-headline text-lg font-bold text-on-surface line-clamp-2 mt-2") }) {
                    Text(previousPost.title)
                }
            }
        } else {
            Div()
        }

        if (nextPost != null) {
            Anchor(
                href = nextPost.route,
                attrs = {
                    tw(
                        "surface-island surface-island-interactive flex flex-col gap-1 p-5 md:p-6 group text-right",
                    )
                },
            ) {
                Span({ tw("font-label text-xs uppercase tracking-wider text-outline group-hover:text-primary transition-colors") }) {
                    Text(Strings.commons.actions.nextPost)
                }
                Span({ tw("font-headline text-lg font-bold text-on-surface line-clamp-2 mt-2") }) {
                    Text(nextPost.title)
                }
            }
        } else {
            Div()
        }
    }
}
