package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.selectors.hover
import org.jetbrains.compose.web.css.*
import vn.id.tozydev.lucidabyss.theme.toColorScheme

val IconButtonVariant =
    ButtonStyle.addVariant {
        val colorScheme = colorMode.toColorScheme()
        base {
            Modifier
                .padding(0.cssRem)
                .borderRadius(50.percent)
                .border(1.px, LineStyle.Solid, colorScheme.outline)
                .backgroundColor(Color.transparent)
                .size(3.cssRem)
        }

        hover {
            Modifier
                .backgroundColor(colorScheme.surfaceVariant)
        }
    }

@Composable
fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Button(
        onClick = { onClick() },
        variant = IconButtonVariant,
    ) {
        content()
    }
}
