package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.ListStyle
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.children
import com.varabyte.kobweb.silk.style.selectors.descendants
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLHeadingElement
import vn.id.tozydev.lucidabyss.utils.getHeadings
import vn.id.tozydev.lucidabyss.utils.level

data class HeadingItem(
    val heading: HTMLHeadingElement,
    val children: List<HeadingItem> = emptyList(),
)

fun HTMLElement.getHeadingHierarchy(
    minHeaderLevel: Int = 2,
    maxHeaderLevel: Int = 3,
): List<HeadingItem> {
    val headings = getHeadings(minHeaderLevel, maxHeaderLevel)
    val rootItems = mutableListOf<HeadingItem>()

    fun processHeading(index: Int): Pair<HeadingItem, Int> {
        val heading = headings[index]
        val children = mutableListOf<HeadingItem>()
        var nextIndex = index + 1

        while (nextIndex < headings.size && headings[nextIndex].level > heading.level) {
            val (childItem, updatedIndex) = processHeading(nextIndex)
            children += childItem
            nextIndex = updatedIndex
        }

        return HeadingItem(heading, children) to nextIndex
    }

    var currentIndex = 0
    while (currentIndex < headings.size) {
        val (item, nextIndex) = processHeading(currentIndex)
        rootItems += item
        currentIndex = nextIndex
    }

    return rootItems
}

val TableOfContentsStyle =
    CssStyle {
        children("ul") {
            Modifier.border {
                style(left = LineStyle.Solid)
                width(1.px)
                color(Colors.LightGray)
            }
        }
        descendants("ul") {
            Modifier
                .listStyle(ListStyle.None)
                .margin(0.px)
                .padding(left = 1.cssRem)
        }
        descendants("a") {
            Modifier
                .textDecorationLine(TextDecorationLine.None)
        }
    }

@Composable
fun TableOfContents(
    hierarchy: List<HeadingItem>,
    modifier: Modifier = Modifier,
) {
    Nav(
        TableOfContentsStyle
            .toModifier()
            .then(modifier)
            .toAttrs(),
    ) {
        Ul {
            hierarchy.forEach { item ->
                TocItem(item)
            }
        }
    }
}

@Composable
private fun TocItem(item: HeadingItem) {
    Li {
        Link("#${item.heading.id}") {
            Text(item.heading.textContent.toString())
        }
        if (item.children.isNotEmpty()) {
            Ul {
                item.children.forEach { child ->
                    TocItem(child)
                }
            }
        }
    }
}
