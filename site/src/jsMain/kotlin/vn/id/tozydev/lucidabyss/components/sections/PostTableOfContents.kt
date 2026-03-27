package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.dom.observers.IntersectionObserver
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLHeadingElement
import vn.id.tozydev.lucidabyss.components.widgets.MaterialSymbol
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun PostTableOfContents(
    headings: List<HTMLHeadingElement>,
    modifier: Modifier = Modifier,
    intersectionObserverOptions: IntersectionObserver.Options? = null,
) {
    var currentId by remember { mutableStateOf<String?>(null) }

    Aside(
        Modifier
            .tw("hidden lg:block lg:w-65 sticky top-32 space-y-6")
            .then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("bg-surface-container-lowest p-6 rounded-2xl border border-surface-variant/30 shadow-sm") }) {
            H3({ tw("font-headline font-extrabold text-primary text-base mb-4 flex items-center gap-2") }) {
                MaterialSymbol("format_list_bulleted", Modifier.tw("text-secondary text-lg"))
                Text(Strings.widget.tableOfContents.title)
            }

            PostStaticTableOfContents(
                headings = headings,
                currentId = currentId,
            )
        }
    }

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
                    currentId = headings.firstOrNull { it.id in headingsInRange }?.id
                    return@IntersectionObserver
                }

                val nextHeading = entries.firstOrNull { it.boundingClientRect.top > it.rootBounds.top }?.target
                if (nextHeading != null) {
                    currentId = headings.getOrNull(headings.indexOf(nextHeading) - 1)?.id
                } else if (currentId == null) {
                    // Handle case where page starts past the last heading
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
}

@Composable
private fun PostStaticTableOfContents(
    headings: List<HTMLHeadingElement>,
    currentId: String? = null,
) {
    Nav({ tw("flex flex-col gap-1") }) {
        headings.forEach { item ->
            TocItem(item, currentId)
        }
    }
}

@Composable
private fun TocItem(
    heading: HTMLHeadingElement,
    currentId: String?,
) {
    val isActive = currentId == heading.id
    Anchor(
        href = "#${heading.id}",
        attrs = {
            tw("block transition-colors pl-3 text-[14px] font-headline font-medium py-1.5")
            if (isActive) {
                tw("text-primary border-l-2 border-primary")
            } else {
                tw("text-on-surface-variant hover:text-primary")
            }
        },
    ) {
        Text(heading.textContent.toString())
    }
}
