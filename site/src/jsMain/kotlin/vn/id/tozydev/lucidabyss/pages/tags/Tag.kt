package vn.id.tozydev.lucidabyss.pages.tags

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import vn.id.tozydev.lucidabyss.components.layouts.PAGE_LAYOUT_FNQ
import vn.id.tozydev.lucidabyss.components.layouts.PageProperties
import vn.id.tozydev.lucidabyss.components.sections.blog.BlogListingContent
import vn.id.tozydev.lucidabyss.generated.Post
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.allPostTags
import vn.id.tozydev.lucidabyss.utils.postsForTag

@InitRoute
fun initTagPage(ctx: InitRouteContext) {
    val tag = ctx.route.params["tag"] ?: return
    ctx.data.add(
        PageProperties(
            title =
                Strings.page.tag.header
                    .title(tag),
            description =
                Strings.page.tag.header
                    .description(tag),
        ),
    )
}

@Page("{tag}")
@Layout(PAGE_LAYOUT_FNQ)
@Composable
fun TagPage(ctx: PageContext) {
    val tag = remember(ctx.route.params) { ctx.route.params["tag"] ?: "" }
    val filteredPosts = remember(tag) { postsForTag(tag) }
    val tags = remember { allPostTags }

    TagPageContent(
        posts = filteredPosts,
        tags = tags,
        title =
            Strings.page.tag.header
                .title(tag),
        description =
            Strings.page.tag.header
                .description(tag),
        activeTag = tag,
    )
}

@Composable
private fun TagPageContent(
    posts: List<Post>,
    tags: List<String>,
    title: String,
    description: String,
    activeTag: String,
) {
    BlogListingContent(
        posts = posts,
        tags = tags,
        title = title,
        description = description,
        activeTag = activeTag,
    )
}
