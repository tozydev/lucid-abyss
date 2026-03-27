package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.core.Links
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun SiteFooter(modifier: Modifier = Modifier) {
    Footer(
        Modifier
            .tw(
                "mx-4 md:mx-auto max-w-[800px] flex flex-col md:flex-row justify-between items-center p-6 md:p-8 gap-4 bg-surface-container-lowest rounded-xl mb-24 md:mb-8 shadow-[0_20px_40px_rgba(42,40,37,0.06)] border-none",
            ).then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("text-on-surface-variant text-sm font-body text-center md:text-left") }) {
            Text(Strings.section.footer.copyright)
        }
        Div({ tw("flex items-center gap-6") }) {
            FooterLink(href = "#", label = Strings.section.footer.link.twitter)
            FooterLink(href = Links.GITHUB_URL, label = Strings.section.footer.link.github)
            FooterLink(href = Links.LINKEDIN_URL, label = Strings.section.footer.link.linkedin)
            FooterLink(href = "#", label = Strings.section.footer.link.privacy)
        }
    }
}

@Composable
private fun FooterLink(
    href: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    Anchor(
        href = href,
        attrs =
            Modifier
                .tw("text-xs font-label uppercase tracking-widest text-on-surface-variant hover:text-primary transition-colors")
                .then(modifier)
                .toAttrs(),
    ) {
        Text(label)
    }
}
