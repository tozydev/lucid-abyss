package vn.id.tozydev.lucidabyss.pages.blog

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
import vn.id.tozydev.lucidabyss.utils.BLOG_YEAR_PARAM
import vn.id.tozydev.lucidabyss.utils.allPostTags
import vn.id.tozydev.lucidabyss.utils.allPostYears
import vn.id.tozydev.lucidabyss.utils.postsForYear
import vn.id.tozydev.lucidabyss.utils.resolveBlogYear

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
fun BlogPage(ctx: PageContext) {
    val years = remember { allPostYears }
    val requestedYear = ctx.route.params[BLOG_YEAR_PARAM]?.toIntOrNull()
    val currentYear = resolveBlogYear(requestedYear)
    val posts = remember(currentYear) { currentYear?.let(::postsForYear).orEmpty() }
    val tags = remember { allPostTags }

    BlogPageContent(
        posts = posts,
        tags = tags,
        years = years,
        currentYear = currentYear,
    )
}

@Composable
internal fun BlogPageContent(
    posts: List<Post>,
    tags: List<String>,
    title: String = Strings.pages.blog.header.title,
    description: String = Strings.pages.blog.header.description,
    activeTag: String = "",
    years: List<Int> = emptyList(),
    currentYear: Int? = null,
) {
    BlogListingContent(
        posts = posts,
        tags = tags,
        title = title,
        description = description,
        activeTag = activeTag,
        years = years,
        currentYear = currentYear,
    )
}
