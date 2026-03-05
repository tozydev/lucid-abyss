package vn.id.tozydev.lucidabyss.components.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaLayerGroup
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutTechStack(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw(
                "card card-border bg-base-100 card-lg hover-primary-glow",
            ).then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body gap-4") }) {
            H2({ tw("card-title text-base") }) {
                FaLayerGroup()
                Text(Strings.widget.about.techstack.title)
            }
            Div({ tw("flex flex-col gap-4") }) {
                Div {
                    H3({ tw("text-sm font-semibold mb-2 text-base-content/70") }) {
                        Text(Strings.widget.about.techstack.languagesTitle)
                    }
                    Div({ tw("flex flex-wrap gap-2") }) {
                        Strings.widget.about.techstack.languages.forEach { lang ->
                            Div({ tw("badge badge-outline badge-lg") }) {
                                Text(lang)
                            }
                        }
                    }
                }
                Div {
                    H3({ tw("text-sm font-semibold mb-2 text-base-content/70") }) {
                        Text(Strings.widget.about.techstack.librariesTitle)
                    }
                    Div({ tw("flex flex-wrap gap-2") }) {
                        Strings.widget.about.techstack.libraries.forEach { lib ->
                            Div({ tw("badge badge-outline badge-lg") }) {
                                Text(lib)
                            }
                        }
                    }
                }
            }
        }
    }
}
