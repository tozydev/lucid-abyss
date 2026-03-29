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
import vn.id.tozydev.lucidabyss.components.sections.BlogListingContent
import vn.id.tozydev.lucidabyss.generated.Posts
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.allTags

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
    val allPosts = Posts
    val filteredPosts =
        remember(allPosts, tag) {
            allPosts
                .filter { it.tags.contains(tag) }
                .sortedByDescending { it.publishedAt }
        }
    val tags =
        remember(allPosts) {
            allPosts.allTags()
        }

    BlogListingContent(
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
