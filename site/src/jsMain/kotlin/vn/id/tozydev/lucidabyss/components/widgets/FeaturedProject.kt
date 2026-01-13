package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRightLong
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.models.Constants
import vn.id.tozydev.lucidabyss.models.Constants.EMAIL_HASH
import vn.id.tozydev.lucidabyss.utils.getGravatarUrl
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun FeaturedProject(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw("card bg-base-100 card-border card-lg")
            .then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body") }) {
            Div({ tw("flex justify-between items-center") }) {
                Div({ tw("badge badge-accent") }) {
                    Text("Nổi bật")
                }

                Div({ tw("avatar") }) {
                    Div({ tw("w-8 rounded-full") }) {
                        Img(
                            src = getGravatarUrl(EMAIL_HASH, 32),
                            alt = "Project author avatar",
                        )
                    }
                }
            }
            H3({ tw("card-title") }) { Text("lucid-abyss") }
            P {
                Text("Website cá nhân của tôi, được xây dựng bằng Kotlin/JS và Kobweb.")
            }
            Div({ tw("card-actions") }) {
                Anchor(
                    href = Constants.LUCID_ABYSS_GITHUB_URL,
                ) {
                    Text("Xem chi tiết")
                    FaArrowRightLong(Modifier.tw("ml-2"))
                }
            }
        }
    }
}
