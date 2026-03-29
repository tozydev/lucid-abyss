package vn.id.tozydev.lucidabyss.pages.blog

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
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
fun initBlogIndexPage(ctx: InitRouteContext) {
    ctx.data.add(
        PageProperties(
            title = Strings.page.blog.title,
            description = Strings.page.blog.description,
        ),
    )
}

@Layout(PAGE_LAYOUT_FNQ)
@Page
@Composable
fun BlogIndexPage() {
    val posts = Posts
    val tags = remember(posts) { posts.allTags() }

    BlogListingContent(
        posts = posts,
        tags = tags,
    )
}
