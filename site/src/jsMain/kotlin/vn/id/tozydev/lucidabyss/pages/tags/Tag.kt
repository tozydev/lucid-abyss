package vn.id.tozydev.lucidabyss.pages.tags

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
    val allPosts = BlogPosts[SiteLanguage.Default] ?: emptyList()
    val filteredPosts =
        remember(allPosts, tag) {
            allPosts
                .filter { it.tags.contains(tag) }
                .sortedByDescending { it.publishedAt }
        }
    val tags =
        remember(allPosts) {
            allPosts
                .flatMap { it.tags }
                .distinct()
                .filter { it.isNotBlank() }
        }

    Div({ tw("max-w-275 mx-auto w-full md:w-auto") }) {
        BlogHeader(
            title =
                Strings.page.tag.header
                    .title(tag),
            description =
                Strings.page.tag.header
                    .description(tag),
        )
        BlogFilters(tags = tags, activeTag = tag)
        BlogGrid(posts = filteredPosts)
        Pagination(
            currentPage = 1,
            totalPages = 1,
            onPageChange = { /* Handle page change */ },
        )
    }
}
