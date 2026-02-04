package vn.id.tozydev.lucidabyss.components.home

import Res
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.navigation.BasePath
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRightLong
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.models.Constants
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun HomeFeaturedProject(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw("card bg-base-100 card-border card-lg")
            .then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body") }) {
            Div({ tw("flex justify-between items-center") }) {
                Div({ tw("badge badge-accent") }) {
                    Text(Res.string.widget_featured_project_badge)
                }

                Div({ tw("avatar") }) {
                    Div({ tw("w-8 rounded-full") }) {
                        Img(
                            src = BasePath.prependTo("/images/avatar_32x.webp"),
                            alt = Res.string.widget_featured_project_author_avatar_alt,
                        )
                    }
                }
            }
            H3({ tw("card-title") }) { Text("lucid-abyss") }
            P {
                Text(Res.string.widget_featured_project_description)
            }
            Div({ tw("card-actions") }) {
                Anchor(
                    href = Constants.LUCID_ABYSS_GITHUB_URL,
                ) {
                    Text(Res.string.widget_featured_project_view_details)
                    FaArrowRightLong(Modifier.tw("ml-2"))
                }
            }
        }
    }
}
