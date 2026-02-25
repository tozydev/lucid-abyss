package vn.id.tozydev.lucidabyss.components.home

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.components.icons.fa.FaBluesky
import com.varabyte.kobweb.silk.components.icons.fa.FaEnvelope
import com.varabyte.kobweb.silk.components.icons.fa.FaGithub
import com.varabyte.kobweb.silk.components.icons.fa.FaLinkedin
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.core.Links
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun HomeSocials(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw("grid grid-cols-2 gap-4 md:grid-cols-4")
            .then(modifier)
            .toAttrs(),
    ) {
        SocialLink(
            path = Links.GITHUB_URL,
            icon = { FaGithub() },
            label = Strings.section.socials.label.github,
        )
        SocialLink(
            path = Links.LINKEDIN_URL,
            icon = { FaLinkedin() },
            label = Strings.section.socials.label.linkedin,
        )
        SocialLink(
            path = Links.BLUESKY_URL,
            icon = { FaBluesky() },
            label = Strings.section.socials.label.bluesky,
        )
        SocialLink(
            path = Links.EMAIL_URL,
            icon = { FaEnvelope() },
            label = Strings.section.socials.label.email,
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
        attrs = {
            tw(
                "card card-border bg-base-100 card-lg hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] hover:-translate-y-1 transition-all duration-300",
            )
        },
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
