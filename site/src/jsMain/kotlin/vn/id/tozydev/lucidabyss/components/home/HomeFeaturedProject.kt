package vn.id.tozydev.lucidabyss.components.home

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.navigation.BasePath
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRightLong
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.core.Links
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun HomeFeaturedProject(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw(
                "card bg-base-100 card-border card-lg hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] transition-all duration-300",
            ).then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body") }) {
            Div({ tw("flex justify-between items-center") }) {
                Div({ tw("badge badge-accent") }) {
                    Text(Strings.widget.featuredProject.badge)
                }

                Div({ tw("avatar") }) {
                    Div({ tw("w-8 rounded-full") }) {
                        Img(
                            src = BasePath.prependTo("/images/avatar_32x.webp"),
                            alt = Strings.widget.featuredProject.authorAvatarAlt,
                        )
                    }
                }
            }
            H3({ tw("card-title") }) { Text("lucid-abyss") }
            P {
                Text(Strings.widget.featuredProject.description)
            }
            Div({ tw("card-actions") }) {
                Anchor(
                    href = Links.LUCID_ABYSS_GITHUB_URL,
                ) {
                    Text(Strings.widget.featuredProject.viewDetails)
                    FaArrowRightLong(Modifier.tw("ml-2"))
                }
            }
        }
    }
}
