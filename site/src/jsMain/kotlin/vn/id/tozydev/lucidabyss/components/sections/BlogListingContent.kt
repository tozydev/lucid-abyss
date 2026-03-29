package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.Pagination
import vn.id.tozydev.lucidabyss.generated.Post
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun BlogListingContent(
    posts: List<Post>,
    tags: List<String>,
    modifier: Modifier = Modifier,
    title: String = Strings.page.blog.header.title,
    description: String = Strings.page.blog.header.description,
    activeTag: String = "",
) {
    Div(
        Modifier
            .tw("max-w-275 mx-auto w-full md:w-auto")
            .then(modifier)
            .toAttrs(),
    ) {
        BlogHeader(
            title = title,
            description = description,
        )
        BlogFilters(tags = tags, activeTag = activeTag)
        BlogGrid(posts = posts)
        Pagination(
            currentPage = 1,
            totalPages = 1,
            onPageChange = { /* Handle page change */ },
        )
    }
}
