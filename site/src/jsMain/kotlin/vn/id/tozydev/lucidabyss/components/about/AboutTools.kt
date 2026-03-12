package vn.id.tozydev.lucidabyss.components.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaWrench
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.core.Profile
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutTools(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw(
                "card card-border bg-base-100 card-lg hover-primary-glow",
            ).then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body") }) {
            H2({ tw("card-title text-base mb-2") }) {
                FaWrench()
                Text(Strings.widget.about.tools.title)
            }
            Div({ tw("flex flex-wrap gap-2") }) {
                Profile.tools.forEach { tool ->
                    Div({ tw("badge badge-neutral badge-lg") }) {
                        Text(tool)
                    }
                }
            }
        }
    }
}
