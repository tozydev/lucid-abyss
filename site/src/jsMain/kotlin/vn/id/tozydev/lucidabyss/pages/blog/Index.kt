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
import vn.id.tozydev.lucidabyss.utils.BLOG_TOPIC_PARAM
import vn.id.tozydev.lucidabyss.utils.BLOG_YEAR_PARAM
import vn.id.tozydev.lucidabyss.utils.SiteRoutes
import vn.id.tozydev.lucidabyss.utils.allPostTopics
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
    val selectedTopic = ctx.route.params[BLOG_TOPIC_PARAM]?.takeIf { it.isNotBlank() }
    val requestedYear = ctx.route.params[BLOG_YEAR_PARAM]?.toIntOrNull()
    val years =
        remember(selectedTopic) {
            if (selectedTopic == null) {
                allPostYears
            } else {
                allPostYears.filter { year ->
                    postsForYear(year).any { post -> post.topic == selectedTopic }
                }
            }
        }
    val currentYear = remember(requestedYear, years) { resolveBlogYear(requestedYear, years) }
    val posts =
        remember(currentYear, selectedTopic) {
            currentYear
                ?.let(::postsForYear)
                .orEmpty()
                .let { yearPosts ->
                    if (selectedTopic == null) {
                        yearPosts
                    } else {
                        yearPosts.filter { post -> post.topic == selectedTopic }
                    }
                }
        }
    val topics = remember { allPostTopics }

    BlogPageContent(
        posts = posts,
        filters = topics,
        activeFilter = selectedTopic.orEmpty(),
        years = years,
        currentYear = currentYear,
        selectedTopic = selectedTopic.orEmpty(),
    )
}

@Composable
internal fun BlogPageContent(
    posts: List<Post>,
    filters: List<String>,
    title: String = Strings.pages.blog.header.title,
    description: String = Strings.pages.blog.header.description,
    activeFilter: String = "",
    years: List<Int> = emptyList(),
    currentYear: Int? = null,
    selectedTopic: String = "",
) {
    BlogListingContent(
        posts = posts,
        filters = filters,
        hrefForFilter = { topic -> SiteRoutes.blog(topic = topic) },
        title = title,
        description = description,
        activeFilter = activeFilter,
        years = years,
        currentYear = currentYear,
        selectedTopic = selectedTopic,
    )
}
