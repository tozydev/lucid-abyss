package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun ExpandableChipRail(
    modifier: Modifier = Modifier,
    rowClass: String = "gap-2",
    content: @Composable () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var measurementRailElement by remember { mutableStateOf<HTMLElement?>(null) }
    var isOverflowing by remember { mutableStateOf(false) }
    val chipsContent = remember(content) { movableContentOf { content() } }

    fun updateOverflowState() {
        val element = measurementRailElement ?: return
        isOverflowing = element.scrollWidth > element.clientWidth + 1
    }

    DisposableEffect(measurementRailElement) {
        updateOverflowState()

        val handleResize = { _: Event -> updateOverflowState() }
        window.addEventListener("resize", handleResize)

        onDispose {
            window.removeEventListener("resize", handleResize)
        }
    }

    val showToggle = isOverflowing
    val visibleRailClasses =
        if (expanded) {
            "flex flex-wrap items-center py-1 $rowClass"
        } else {
            "flex flex-nowrap items-center py-1 $rowClass overflow-x-auto overflow-y-visible hide-scrollbar"
        }

    Div(
        Modifier
            .tw("relative w-full min-w-0")
            .then(modifier)
            .toAttrs(),
    ) {
        Div(
            {
                tw("absolute inset-0 -z-10 invisible pointer-events-none")
                attr("aria-hidden", "true")
            },
        ) {
            Div(
                {
                    tw("flex flex-nowrap items-center $rowClass overflow-x-auto overflow-y-hidden")
                    ref {
                        measurementRailElement = it as? HTMLElement
                        onDispose { }
                    }
                },
            ) {
                chipsContent()
            }
        }

        Div({ tw("flex items-start gap-2 w-full min-w-0") }) {
            Div({ tw(visibleRailClasses) }) {
                chipsContent()
            }

            if (showToggle) {
                Button(
                    {
                        tw(
                            "shrink-0 inline-flex h-10 w-10 mt-1 items-center justify-center rounded-full border border-surface-variant bg-surface-container-high text-on-surface-variant transition hover:bg-primary-container hover:text-on-primary-container",
                        )
                        attr(
                            "aria-label",
                            if (expanded) Strings.commons.labels.collapseChips else Strings.commons.labels.expandChips,
                        )
                        attr("aria-expanded", expanded.toString())
                        onClick {
                            expanded = !expanded
                        }
                    },
                ) {
                    if (expanded) {
                        KeyboardArrowUpIcon()
                    } else {
                        KeyboardArrowDownIcon()
                    }
                }
            }
        }
    }
}
