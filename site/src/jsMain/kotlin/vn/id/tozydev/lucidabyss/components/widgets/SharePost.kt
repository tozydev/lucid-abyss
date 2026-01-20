package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.components.icons.fa.FaBluesky
import com.varabyte.kobweb.silk.components.icons.fa.FaFacebook
import com.varabyte.kobweb.silk.components.icons.fa.FaLink
import com.varabyte.kobweb.silk.components.icons.fa.FaLinkedin
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun SharePost(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw("card card-border bg-base-100")
            .then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body items-center text-center lg:text-start lg:items-start") }) {
            H3({ tw("card-title text-base mb-2") }) {
                Text(Res.string.widget_share_post_title)
            }

            Div({ tw("flex items-center gap-4 lg:gap-2") }) {
                Anchor(
                    href = "",
                    attrs = { tw("btn btn-circle") },
                ) {
                    FaFacebook()
                }
                Anchor(
                    href = "",
                    attrs = { tw("btn btn-circle") },
                ) {
                    FaLinkedin()
                }
                Anchor(
                    href = "",
                    attrs = { tw("btn btn-circle") },
                ) {
                    FaBluesky()
                }
                Anchor(
                    href = "",
                    attrs = { tw("btn btn-circle") },
                ) {
                    FaLink()
                }
            }
        }
    }
}
