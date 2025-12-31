package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.icons.fa.FaChevronLeft
import com.varabyte.kobweb.silk.components.icons.fa.FaChevronRight
import com.varabyte.kobweb.silk.style.addVariant
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

val PaginationIslandVariant =
    IslandStyle.addVariant {
        base {
            Modifier
                .padding(0.5.cssRem)
                .gap(0.5.cssRem)
                .alignItems(AlignItems.Center)
        }
    }

@Composable
fun Pagination(modifier: Modifier = Modifier) {
    Island(
        modifier = modifier,
        variant = RowIslandVariant.then(PaginationIslandVariant),
    ) {
        PaginationItem { FaChevronLeft() }
        PaginationItem { Text("1") }
        PaginationItem { Text("2") }
        PaginationItem { Text("...") }
        PaginationItem { Text("5") }
        PaginationItem { FaChevronRight() }
    }
}

val PaginationItemButtonVariant =
    ButtonStyle.addVariant {
        base {
            Modifier
                .padding(topBottom = 0.5.cssRem, leftRight = 1.cssRem)
                .borderRadius(0.75.cssRem)
        }
    }

@Composable
private fun PaginationItem(content: @Composable () -> Unit) {
    Button(
        onClick = {},
        variant = PaginationItemButtonVariant,
    ) {
        content()
    }
}
