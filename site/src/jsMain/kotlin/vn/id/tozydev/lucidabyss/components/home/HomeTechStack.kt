package vn.id.tozydev.lucidabyss.components.home

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaLayerGroup
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun HomeTechStack(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw(
                "card card-border bg-base-100 card-lg hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] transition-all duration-300",
            ).then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body") }) {
            H2({ tw("card-title text-base") }) {
                FaLayerGroup()
                Text(Strings.section.techstack.title)
            }
            Div({ tw("flex flex-wrap gap-2") }) {
                Div({ tw("badge badge-outline badge-lg") }) {
                    Text(Strings.section.techstack.badge.java)
                }
                Div({ tw("badge badge-outline badge-lg") }) {
                    Text(Strings.section.techstack.badge.kotlin)
                }
                Div({ tw("badge badge-outline badge-lg") }) {
                    Text(Strings.section.techstack.badge.kobweb)
                }
            }
        }
    }
}
