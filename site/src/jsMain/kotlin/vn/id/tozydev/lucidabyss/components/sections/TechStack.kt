package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaLayerGroup
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun TechStack(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw("card card-border bg-base-100 card-lg")
            .then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body") }) {
            H2({ tw("card-title text-base") }) {
                FaLayerGroup()
                Text("Tech Stack")
            }
            Div({ tw("flex flex-wrap gap-2") }) {
                Div({ tw("badge badge-neutral badge-outline badge-lg") }) {
                    Text("Java")
                }
                Div({ tw("badge badge-neutral badge-outline badge-lg") }) {
                    Text("Kotlin")
                }
                Div({ tw("badge badge-neutral badge-outline badge-lg") }) {
                    Text("Kobweb")
                }
            }
        }
    }
}
