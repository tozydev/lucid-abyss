package vn.id.tozydev.lucidabyss.pages.blog

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import vn.id.tozydev.lucidabyss.components.layouts.PAGE_LAYOUT_FNQ
import vn.id.tozydev.lucidabyss.components.layouts.PageProperties
import vn.id.tozydev.lucidabyss.components.sections.blog.BlogListingContent
import vn.id.tozydev.lucidabyss.generated.Post
import vn.id.tozydev.lucidabyss.generated.Posts
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.allPostTags

@InitRoute
fun initBlogPage(ctx: InitRouteContext) {
    ctx.data.add(
        PageProperties(
            title = Strings.pages.blog.title,
            description = Strings.pages.blog.description,
        ),
    )
}

@Layout(PAGE_LAYOUT_FNQ)
@Page
@Composable
fun BlogPage() {
    val posts = Posts
    val tags = remember { allPostTags }

    BlogPageContent(
        posts = posts,
        tags = tags,
    )
}

@Composable
private fun BlogPageContent(
    posts: List<Post>,
    tags: List<String>,
    title: String = Strings.pages.blog.header.title,
    description: String = Strings.pages.blog.header.description,
    activeTag: String = "",
) {
    BlogListingContent(
        posts = posts,
        tags = tags,
        title = title,
        description = description,
        activeTag = activeTag,
    )
}
