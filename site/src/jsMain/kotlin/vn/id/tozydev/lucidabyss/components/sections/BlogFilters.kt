package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.Badge
import vn.id.tozydev.lucidabyss.components.widgets.BadgeSize
import vn.id.tozydev.lucidabyss.components.widgets.BadgeVariant
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.SiteRoutes
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun BlogFilters(
    tags: List<String>,
    modifier: Modifier = Modifier,
    activeTag: String = "",
) {
    Section(
        Modifier
            .tw("mb-12 flex flex-wrap gap-2 items-center")
            .then(modifier)
            .toAttrs(),
    ) {
        Badge(
            text = Strings.page.blog.all,
            href = SiteRoutes.blog,
            variant = if (activeTag.isEmpty()) BadgeVariant.Primary else BadgeVariant.Surface,
            size = BadgeSize.Lg,
            modifier = Modifier.tw("shadow-sm font-bold"),
        )
        tags.forEach { tag ->
            Badge(
                text = tag,
                href = SiteRoutes.tag(tag),
                variant = if (activeTag == tag) BadgeVariant.Primary else BadgeVariant.Surface,
                size = BadgeSize.Lg,
                modifier = Modifier.tw("shadow-sm font-bold"),
            )
        }
    }
}
