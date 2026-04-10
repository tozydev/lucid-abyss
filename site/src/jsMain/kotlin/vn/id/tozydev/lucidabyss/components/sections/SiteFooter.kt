package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.strings.builtWith
import vn.id.tozydev.lucidabyss.strings.copyright
import vn.id.tozydev.lucidabyss.utils.Links
import vn.id.tozydev.lucidabyss.utils.SiteRoutes
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun SiteFooter(modifier: Modifier = Modifier) {
    Footer(modifier.toAttrs()) {
        Div(
            {
                tw(
                    "flex flex-col md:flex-row items-center p-6 justify-between gap-4 bg-surface-container-lowest rounded-xl shadow-soft border-none",
                )
            },
        ) {
            Div({ tw("text-on-surface-variant text-sm font-body text-center md:text-left tracking-wide") }) {
                Aside {
                    P {
                        Strings.sections.footer.copyright { first, github ->
                            Text(first)
                            Anchor(href = Strings.profile.siteSource, attrs = { tw("link link-hover") }) {
                                Text(github)
                            }
                        }
                    }
                    P {
                        Strings.sections.footer.builtWith { first, kotlin, separator, kobweb ->
                            Text(first)
                            Anchor(href = Links.KOTLIN_HOMEPAGE_URL, attrs = { tw("link link-hover") }) {
                                Text(kotlin)
                            }
                            Text(separator)
                            Anchor(href = Links.KOBWEB_HOMEPAGE_URL, attrs = { tw("link link-hover") }) {
                                Text(kobweb)
                            }
                        }
                    }
                }
            }
            Nav({ tw("grid grid-flow-col gap-4") }) {
                FooterLink(href = Strings.profile.contact.github, label = Strings.commons.labels.github)
                FooterLink(href = Strings.profile.contact.bluesky, label = Strings.commons.labels.bluesky)
                FooterLink(href = "mailto:${Strings.profile.contact.email}", label = Strings.commons.labels.email)
                FooterLink(href = SiteRoutes.rss, label = Strings.commons.labels.rss)
            }
        }
    }
}

@Composable
private fun FooterLink(
    href: String,
    label: String,
) {
    Anchor(
        href = href,
        attrs = {
            tw(
                "text-xs font-label uppercase tracking-wide text-on-surface-variant hover:text-primary transition-colors link-hover",
            )
        },
        openInternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB,
    ) {
        Text(label)
    }
}
