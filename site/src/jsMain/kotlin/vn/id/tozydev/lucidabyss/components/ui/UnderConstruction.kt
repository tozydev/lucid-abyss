package vn.id.tozydev.lucidabyss.components.ui

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaWrench
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun UnderConstruction(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Div(
        Modifier
            .tw("card bg-base-100 card-border w-full max-w-lg mx-auto hover-primary-glow")
            .then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body items-center text-center") }) {
            FaWrench(
                modifier = Modifier.tw("text-primary mb-4"),
                size = IconSize.X3,
            )
            H2({ tw("card-title text-2xl mb-2") }) {
                Text(title)
            }
            P({ tw("text-base-content/70") }) {
                Text(description)
            }
        }
    }
}
