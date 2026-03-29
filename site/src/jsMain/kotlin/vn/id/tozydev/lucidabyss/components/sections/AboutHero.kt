package vn.id.tozydev.lucidabyss.components.sections

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
            src = "/images/default-cover.webp",
            alt = Strings.page.about.heroImageAlt,
            attrs =
                Modifier
                    .tw("w-full h-full object-cover")
                    .toAttrs {
                        attr("width", "1600")
                        attr("height", "640")
                        attr("loading", "eager")
                        attr("fetchpriority", "high")
                        attr("decoding", "async")
                    },
        )
        Div(
            Modifier
                .tw("absolute inset-x-0 bottom-0 h-32 bg-linear-to-t from-surface-container-lowest to-transparent")
                .toAttrs(),
        )

        // Profile Image Overlay
        Div(Modifier.tw("absolute -bottom-10 md:-bottom-12 left-1/2 -translate-x-1/2").toAttrs()) {
            Div(
                Modifier
                    .tw(
                        "w-24 h-24 md:w-32 md:h-32 rounded-full border-8 border-surface-container-lowest overflow-hidden shadow-lg bg-surface-container-lowest",
                    ).toAttrs(),
            ) {
                Img(
                    src = "/images/avatar_96x.webp",
                    alt = Strings.page.about.profileImageAlt,
                    attrs =
                        Modifier
                            .tw("w-full h-full object-cover")
                            .toAttrs {
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
