package vn.id.tozydev.lucidabyss.components.blog

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.dom.observers.IntersectionObserver
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLHeadingElement
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun PostTableOfContents(
    headings: List<HTMLHeadingElement>,
    modifier: Modifier = Modifier,
    intersectionObserverOptions: IntersectionObserver.Options? = null,
) {
    var currentId by remember { mutableStateOf<String?>(null) }
    PostStaticTableOfContents(
        headings = headings,
        currentId = currentId,
        modifier = modifier,
    )
    DisposableEffect(headings, intersectionObserverOptions) {
        val headingsInRange = mutableSetOf<String>()

        val observer =
            IntersectionObserver(intersectionObserverOptions) { entries ->
                entries.forEach { entry ->
                    if (entry.isIntersecting) {
                        headingsInRange += entry.target.id
                    } else {
                        headingsInRange -= entry.target.id
                    }
                }

                if (headingsInRange.isNotEmpty()) {
                    currentId = headings.first { it.id in headingsInRange }.id
                    return@IntersectionObserver
                }

                val nextHeading = entries.firstOrNull { it.boundingClientRect.top > it.rootBounds.top }?.target
                if (nextHeading != null) {
                    currentId = headings.getOrNull(headings.indexOf(nextHeading) - 1)?.id
                    return@IntersectionObserver
                }

                if (currentId == null) {
                    // Handle case where page starts past the last heading
                    val lastEntry = entries.last()
                    if (lastEntry.boundingClientRect.top <= lastEntry.rootBounds.top) {
                        currentId = lastEntry.target.id
                    }
                }
            }

        headings.forEach { heading ->
            observer.observe(heading)
        }
        onDispose { observer.disconnect() }
    }
}

@Composable
private fun PostStaticTableOfContents(
    headings: List<HTMLHeadingElement>,
    modifier: Modifier = Modifier,
    currentId: String? = null,
) {
    Nav(
        Modifier
            .then(modifier)
            .toAttrs(),
    ) {
        Ul({ tw("menu p-0 w-full") }) {
            headings.forEach { item ->
                TocItem(item, currentId)
            }
        }
    }
}

@Composable
private fun TocItem(
    heading: HTMLHeadingElement,
    currentId: String?,
) {
    Li {
        Anchor(
            href = "#${heading.id}",
            attrs =
                Modifier
                    .thenIf(currentId == heading.id) {
                        Modifier.tw("menu-active")
                    }.toAttrs(),
        ) {
            Text(heading.textContent.toString())
        }
    }
}
