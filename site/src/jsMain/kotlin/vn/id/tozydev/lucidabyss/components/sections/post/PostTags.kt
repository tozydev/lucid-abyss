package vn.id.tozydev.lucidabyss.components.sections.post

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.Badge
import vn.id.tozydev.lucidabyss.components.widgets.BadgeSize
import vn.id.tozydev.lucidabyss.components.widgets.BadgeVariant
import vn.id.tozydev.lucidabyss.utils.SiteRoutes
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun PostTags(
    tags: Set<String>,
    modifier: Modifier = Modifier,
) {
    if (tags.isEmpty()) return

    Footer(
        Modifier
            .tw("mt-16 pt-8 border-t border-surface-variant flex flex-wrap gap-3")
            .then(modifier)
            .toAttrs(),
    ) {
        tags.forEach { tag ->
            Badge(
                text = "#$tag",
                href = SiteRoutes.tag(tag),
                variant = BadgeVariant.Surface,
                size = BadgeSize.Lg,
                modifier = Modifier.tw("cursor-pointer"),
            )
        }
    }
}
