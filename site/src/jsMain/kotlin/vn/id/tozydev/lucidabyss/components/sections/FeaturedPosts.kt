package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.PostCard
import vn.id.tozydev.lucidabyss.components.widgets.SectionHeader
import vn.id.tozydev.lucidabyss.generated.Post
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.SiteRoutes
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun FeaturedPosts(
    posts: List<Post>,
    modifier: Modifier = Modifier,
) {
    Section(Modifier.tw("mb-32").then(modifier).toAttrs()) {
        SectionHeader(
            title = Strings.section.featuredPosts.title,
            description = Strings.section.featuredPosts.description,
            linkText = Strings.section.featuredPosts.viewAll,
            linkHref = SiteRoutes.blog,
        )
        Div(Modifier.tw("grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8").toAttrs()) {
            posts.forEach { post ->
                key(post.slug) {
                    PostCard(post)
                }
            }
        }
    }
}
