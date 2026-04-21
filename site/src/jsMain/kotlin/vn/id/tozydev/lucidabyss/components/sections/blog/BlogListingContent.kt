package vn.id.tozydev.lucidabyss.components.sections.blog

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.Pagination
import vn.id.tozydev.lucidabyss.generated.Post
import vn.id.tozydev.lucidabyss.utils.SiteRoutes
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun BlogListingContent(
    posts: List<Post>,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    filters: List<String> = emptyList(),
    hrefForFilter: ((String) -> String)? = null,
    activeFilter: String = "",
    years: List<Int> = emptyList(),
    currentYear: Int? = null,
    selectedTopic: String = "",
) {
    val selectedYear = currentYear?.takeIf { it in years }

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
        if (filters.isNotEmpty() && hrefForFilter != null) {
            BlogFilters(
                items = filters,
                itemHref = hrefForFilter,
                activeItem = activeFilter,
            )
        }
        BlogGrid(posts = posts)
        if (years.size > 1 && selectedYear != null) {
            Pagination(
                pages = years,
                currentPage = selectedYear,
                hrefForPage = { year -> SiteRoutes.blog(year = year, topic = selectedTopic.takeIf { it.isNotBlank() }) },
            )
        }
    }
}
