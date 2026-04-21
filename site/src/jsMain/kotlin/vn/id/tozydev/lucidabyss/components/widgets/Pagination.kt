package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun Pagination(
    pages: List<Int>,
    currentPage: Int,
    hrefForPage: (Int) -> String,
    modifier: Modifier = Modifier,
) {
    if (pages.isEmpty()) return

    val currentIndex = pages.indexOf(currentPage).takeIf { it >= 0 } ?: return
    val prevPage = pages.getOrNull(currentIndex - 1)
    val nextPage = pages.getOrNull(currentIndex + 1)

    Nav(Modifier.tw("mt-20 flex justify-center items-center gap-3 flex-wrap").then(modifier).toAttrs()) {
        if (prevPage != null) {
            ArchiveArrowLink(
                href = hrefForPage(prevPage),
                contentDescription = prevPage.toString(),
                icon = { ChevronLeftIcon(Modifier.tw("text-lg")) },
            )
        } else {
            ArchiveArrowDisabled {
                ChevronLeftIcon(Modifier.tw("text-lg"))
            }
        }

        pages.forEach { page ->
            val isActive = page == currentPage
            Anchor(
                href = hrefForPage(page),
                attrs = {
                    tw(
                        if (isActive) {
                            "h-12 px-4 flex items-center justify-center rounded-2xl bg-primary text-on-primary font-headline font-bold shadow-lg shadow-primary/20 transition-all"
                        } else {
                            "h-12 px-4 flex items-center justify-center rounded-2xl bg-surface-container text-on-surface-variant hover:bg-surface-container-high transition-colors font-headline font-bold"
                        },
                    )
                    if (isActive) {
                        attr("aria-current", "page")
                    }
                },
            ) {
                Text(page.toString())
            }
        }

        if (nextPage != null) {
            ArchiveArrowLink(
                href = hrefForPage(nextPage),
                contentDescription = nextPage.toString(),
                icon = { ChevronRightIcon(Modifier.tw("text-lg")) },
            )
        } else {
            ArchiveArrowDisabled {
                ChevronRightIcon(Modifier.tw("text-lg"))
            }
        }
    }
}

@Composable
private fun ArchiveArrowLink(
    href: String,
    contentDescription: String,
    icon: @Composable () -> Unit,
) {
    Anchor(
        href = href,
        attrs = {
            tw(
                "h-12 flex items-center justify-center rounded-2xl bg-surface-container text-on-surface-variant hover:bg-surface-container-high transition-colors px-3",
            )
            attr("aria-label", contentDescription)
        },
    ) {
        icon()
    }
}

@Composable
private fun ArchiveArrowDisabled(icon: @Composable () -> Unit) {
    Span(
        Modifier
            .tw(
                "h-12 flex items-center justify-center rounded-2xl bg-surface-container text-on-surface-variant opacity-50 cursor-not-allowed px-3",
            ).toAttrs {
                attr("aria-hidden", "true")
            },
    ) {
        icon()
    }
}
