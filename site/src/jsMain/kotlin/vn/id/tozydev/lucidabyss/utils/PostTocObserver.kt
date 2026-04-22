package vn.id.tozydev.lucidabyss.utils

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.dom.observers.IntersectionObserver
import org.w3c.dom.HTMLHeadingElement

@Composable
fun rememberActiveHeadingId(
    headings: List<HTMLHeadingElement>,
    intersectionObserverOptions: IntersectionObserver.Options? = null,
): String? {
    var currentId by remember { mutableStateOf<String?>(null) }

    DisposableEffect(headings, intersectionObserverOptions) {
        if (headings.isEmpty()) return@DisposableEffect onDispose { }
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
                    currentId = headings.lastOrNull { it.id in headingsInRange }?.id
                    return@IntersectionObserver
                }

                val nextHeading = entries.firstOrNull { it.boundingClientRect.top > it.rootBounds.top }?.target
                if (nextHeading != null) {
                    currentId = headings.getOrNull(headings.indexOf(nextHeading) - 1)?.id
                } else if (currentId == null) {
                    val lastEntry = entries.lastOrNull()
                    if (lastEntry != null && lastEntry.boundingClientRect.top <= lastEntry.rootBounds.top) {
                        currentId = lastEntry.target.id
                    }
                }
            }

        headings.forEach { heading ->
            observer.observe(heading)
        }
        onDispose { observer.disconnect() }
    }

    return currentId
}
