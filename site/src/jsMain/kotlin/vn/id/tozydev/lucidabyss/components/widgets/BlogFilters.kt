package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.style.addVariant
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

val BlogFilterIslandVariant =
    IslandStyle.addVariant {
        base {
            Modifier
                .padding(0.75.cssRem)
                .gap(0.5.cssRem)
        }
    }

@Composable
fun BlogFilters(modifier: Modifier = Modifier) {
    Island(
        modifier = modifier,
        variant = RowIslandVariant.then(BlogFilterIslandVariant),
    ) {
        FilterChip("All")
        FilterChip("Kotlin")
        FilterChip("Java")
    }
}

val FilterChipButtonVariant =
    ButtonStyle.addVariant {
        base {
            Modifier
                .padding(topBottom = 0.5.cssRem, leftRight = 1.cssRem)
                .borderRadius(9999.px)
        }
    }

@Composable
private fun FilterChip(
    label: String,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = {},
        modifier = modifier,
        variant = FilterChipButtonVariant,
    ) {
        Text(label)
    }
}
