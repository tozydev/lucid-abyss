package vn.id.tozydev.lucidabyss.components.blog

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLHeadingElement
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun PostTableOfContents(
    headings: List<HTMLHeadingElement>,
    modifier: Modifier = Modifier,
) {
    Nav(
        Modifier
            .then(modifier)
            .toAttrs(),
    ) {
        Ul({ tw("menu p-0 w-full") }) {
            headings.forEach { item ->
                TocItem(item)
            }
        }
    }
}

@Composable
private fun TocItem(item: HTMLHeadingElement) {
    Li {
        Anchor(
            href = "#${item.id}",
            attrs = { },
        ) {
            Text(item.textContent.toString())
        }
    }
}
