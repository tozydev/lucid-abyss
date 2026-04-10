package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun Pagination(
    currentPage: Int,
    totalPages: Int,
    onPageChange: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Nav(Modifier.tw("mt-20 flex justify-center items-center gap-3").then(modifier).toAttrs()) {
        // Previous Button
        Button(
            attrs =
                Modifier
                    .tw(
                        "h-12 flex items-center justify-center rounded-2xl bg-surface-container text-on-surface-variant hover:bg-surface-container-high transition-colors px-3",
                    ).toAttrs {
                        onClick { if (currentPage > 1) onPageChange(currentPage - 1) }
                        if (currentPage <= 1) {
                            classes("opacity-50", "cursor-not-allowed")
                        }
                    },
        ) {
            ChevronLeftIcon(Modifier.tw("text-lg"))
        }

        // Page Numbers
        for (i in 1..totalPages) {
            val isActive = i == currentPage
            val classes =
                if (isActive) {
                    "h-12 px-4 flex items-center justify-center rounded-2xl bg-primary text-on-primary font-headline font-bold shadow-lg shadow-primary/20 transition-all"
                } else {
                    "h-12 px-4 flex items-center justify-center rounded-2xl bg-surface-container text-on-surface-variant hover:bg-surface-container-high transition-colors font-headline font-bold"
                }

            Button(
                attrs =
                    Modifier
                        .tw(classes)
                        .toAttrs {
                            onClick { onPageChange(i) }
                        },
            ) {
                Text(i.toString())
            }
        }

        // Next Button
        Button(
            attrs =
                Modifier
                    .tw(
                        "h-12 flex items-center justify-center rounded-2xl bg-surface-container text-on-surface-variant hover:bg-surface-container-high transition-colors px-3",
                    ).toAttrs {
                        onClick { if (currentPage < totalPages) onPageChange(currentPage + 1) }
                        if (currentPage >= totalPages) {
                            classes("opacity-50", "cursor-not-allowed")
                        }
                    },
        ) {
            ChevronRightIcon(Modifier.tw("text-lg"))
        }
    }
}
