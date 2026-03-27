package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.PostCard
import vn.id.tozydev.lucidabyss.core.BlogPost
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun BlogGrid(
    posts: List<BlogPost>,
    modifier: Modifier = Modifier,
) {
    Div(
        Modifier
            .tw("grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8")
            .then(modifier)
            .toAttrs(),
    ) {
        posts.forEach { post ->
            key(post.id.value) {
                PostCard(post)
            }
        }
    }
}
