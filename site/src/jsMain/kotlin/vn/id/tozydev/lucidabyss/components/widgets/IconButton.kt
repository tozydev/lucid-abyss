package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.selectors.hover
import org.jetbrains.compose.web.css.*

val IconButtonVariant =
    ButtonStyle.addVariant {
        base {
            Modifier
                .padding(0.cssRem)
                .borderRadius(50.percent)
                .border(1.px, LineStyle.Solid, Colors.LightGray)
                .backgroundColor(Color.transparent)
                .size(3.cssRem)
        }

        hover {
            Modifier
                .backgroundColor(Colors.Gray)
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
        modifier = modifier,
        variant = IconButtonVariant,
    ) {
        content()
    }
}
