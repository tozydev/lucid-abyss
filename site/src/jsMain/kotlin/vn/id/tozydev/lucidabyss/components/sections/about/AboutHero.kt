package vn.id.tozydev.lucidabyss.components.sections.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutHero(modifier: Modifier = Modifier) {
    Div(Modifier.tw("w-full h-62.5 md:h-100 relative").then(modifier).toAttrs()) {
        Img(
            src = "/images/about-hero.webp",
            alt = Strings.pages.about.images.heroAlt,
            attrs = {
                tw("w-full h-full object-cover")
                attr("width", "800")
                attr("height", "400")
                attr("loading", "eager")
                attr("fetchpriority", "high")
                attr("decoding", "async")
            },
        )
        Div({ tw("absolute inset-0 bg-linear-to-t from-surface-container via-transparent via-30% to-transparent") })
        Div({ tw("absolute -bottom-10 md:-bottom-12 left-1/2 -translate-x-1/2") }) {
            Div(
                {
                    tw(
                        "w-24 h-24 md:w-32 md:h-32 rounded-full border-6 border-surface-container-lowest overflow-hidden bg-surface-container-lowest",
                    )
                },
            ) {
                Img(
                    src = "/images/avatar_128x.webp",
                    alt = Strings.pages.about.images.profileAlt,
                    attrs = {
                        tw("w-full h-full object-cover")
                        attr("width", "128")
                        attr("height", "128")
                        attr("loading", "eager")
                        attr("decoding", "async")
                    },
                )
            }
        }
    }
}
