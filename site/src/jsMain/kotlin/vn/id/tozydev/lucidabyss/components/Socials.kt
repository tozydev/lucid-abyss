package vn.id.tozydev.lucidabyss.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.components.icons.fa.FaBluesky
import com.varabyte.kobweb.silk.components.icons.fa.FaEnvelope
import com.varabyte.kobweb.silk.components.icons.fa.FaGithub
import com.varabyte.kobweb.silk.components.icons.fa.FaLinkedin
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.models.Constants
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun Socials(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw("grid grid-cols-2 gap-4 md:grid-cols-4")
            .then(modifier)
            .toAttrs(),
    ) {
        SocialLink(
            path = Constants.GITHUB_URL,
            icon = { FaGithub() },
            label = Res.string.section_socials_label_github,
        )
        SocialLink(
            path = Constants.LINKEDIN_URL,
            icon = { FaLinkedin() },
            label = Res.string.section_socials_label_linkedin,
        )
        SocialLink(
            path = Constants.BLUESKY_URL,
            icon = { FaBluesky() },
            label = Res.string.section_socials_label_bluesky,
        )
        SocialLink(
            path = Constants.EMAIL_URL,
            icon = { FaEnvelope() },
            label = Res.string.section_socials_label_email,
        )
    }
}

@Composable
private fun SocialLink(
    path: String,
    icon: @Composable () -> Unit,
    label: String,
) {
    Anchor(
        href = path,
        attrs = { tw("card card-border bg-base-100 card-lg") },
    ) {
        Div({ tw("card-body items-center text-center") }) {
            Div({ tw("text-3xl") }) {
                icon()
            }
            Div({ tw("text-sm font-medium") }) {
                Text(label)
            }
        }
    }
}
