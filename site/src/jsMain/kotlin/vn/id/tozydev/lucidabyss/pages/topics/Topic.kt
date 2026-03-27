package vn.id.tozydev.lucidabyss.pages.topics

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.layouts.PAGE_LAYOUT_FNQ
import vn.id.tozydev.lucidabyss.components.layouts.PageProperties
import vn.id.tozydev.lucidabyss.components.sections.BlogFilters
import vn.id.tozydev.lucidabyss.components.sections.BlogGrid
import vn.id.tozydev.lucidabyss.components.sections.BlogHeader
import vn.id.tozydev.lucidabyss.components.widgets.Pagination
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.generated.BlogPosts
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

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
    val allPosts = BlogPosts[SiteLanguage.Default] ?: emptyList()
    val filteredPosts =
        remember(allPosts, topic) {
            allPosts
                .filter { it.topic == topic }
                .sortedByDescending { it.publishedAt }
        }
    val tags =
        remember(filteredPosts) {
            filteredPosts
                .flatMap { it.tags }
                .distinct()
                .filter { it.isNotBlank() }
        }

    Div({ tw("max-w-275 mx-auto w-full md:w-auto") }) {
        BlogHeader(
            title =
                Strings.page.topic.header
                    .title(topic),
            description =
                Strings.page.topic.header
                    .description(topic),
        )
        BlogFilters(tags = tags, activeTag = "")
        BlogGrid(posts = filteredPosts)
        Pagination(
            currentPage = 1,
            totalPages = 1,
            onPageChange = { /* Handle page change */ },
        )
    }
}
