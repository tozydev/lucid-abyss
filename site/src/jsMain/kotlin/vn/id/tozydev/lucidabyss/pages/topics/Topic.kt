package vn.id.tozydev.lucidabyss.pages.topics

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import vn.id.tozydev.lucidabyss.components.layouts.PAGE_LAYOUT_FNQ
import vn.id.tozydev.lucidabyss.components.layouts.PageProperties
import vn.id.tozydev.lucidabyss.components.sections.BlogListingContent
import vn.id.tozydev.lucidabyss.generated.Posts
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.allTags

@InitRoute
fun initTopicPage(ctx: InitRouteContext) {
    val topic = ctx.route.params["topic"] ?: return
    ctx.data.add(
        PageProperties(
            title =
                Strings.page.topic.header
                    .title(topic),
            description =
                Strings.page.topic.header
                    .description(topic),
        ),
    )
}

@Page("{topic}")
@Layout(PAGE_LAYOUT_FNQ)
@Composable
fun TopicPage(ctx: PageContext) {
    val topic = remember(ctx.route.params) { ctx.route.params["topic"] ?: "" }
    val allPosts = Posts
    val filteredPosts =
        remember(allPosts, topic) {
            allPosts
                .filter { it.topic == topic }
                .sortedByDescending { it.publishedAt }
        }
    val tags =
        remember(filteredPosts) {
            filteredPosts.allTags()
        }

    BlogListingContent(
        posts = filteredPosts,
        tags = tags,
        title =
            Strings.page.topic.header
                .title(topic),
        description =
            Strings.page.topic.header
                .description(topic),
    )
}
