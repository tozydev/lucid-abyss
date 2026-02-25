package vn.id.tozydev.lucidabyss.components.blog

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun PostTags(
    tags: Set<String>,
    modifier: Modifier = Modifier,
) {
    if (tags.isEmpty()) return

    Div(
        attrs =
            Modifier
                .tw(
                    "card card-border bg-base-100 hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] transition-all duration-300",
                ).then(modifier)
                .toAttrs(),
    ) {
        Div({ tw("card-body flex-row flex-wrap gap-2") }) {
            tags.forEach { tag ->
                Div({ tw("badge badge-outline") }) {
                    Text("#$tag")
                }
            }
        }
    }
}
