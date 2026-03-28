package vn.id.tozydev.lucidabyss.pages.blog

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
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
import vn.id.tozydev.lucidabyss.generated.Posts
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@InitRoute
fun initIndex(ctx: InitRouteContext) {
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
fun Index() {
    val posts = Posts
    val tags = remember(posts) { posts.flatMap { it.tags }.distinct().filter { it.isNotBlank() } }

    Div({ tw("max-w-275 mx-auto w-full md:w-auto") }) {
        BlogHeader()
        BlogFilters(tags = tags)
        BlogGrid(posts = posts)
        Pagination(
            currentPage = 1,
            totalPages = 1,
            onPageChange = { /* Handle page change */ },
        )
    }
}
