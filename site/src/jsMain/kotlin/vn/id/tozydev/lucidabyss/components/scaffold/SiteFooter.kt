package vn.id.tozydev.lucidabyss.components.scaffold

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.core.Links
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.strings.copyright
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun SiteFooter(modifier: Modifier = Modifier) {
    Footer(modifier.toAttrs()) {
        Div(
            {
                tw(
                    "flex flex-col md:flex-row justify-between items-center py-4 px-6 bg-base-100 text-center md:text-left gap-4 rounded-box shadow-sm",
                )
            },
        ) {
            Aside {
                Strings.section.footer.copyright { right, github, builtWith, kotlin, and, kobweb ->
                    P {
                        Text(right)
                        Anchor(
                            href = Links.LUCID_ABYSS_GITHUB_URL,
                            attrs = { tw("link link-hover text-primary") },
                        ) {
                            Text(github)
                        }
                        Text(".")
                    }
                    P {
                        Text(builtWith)
                        Anchor(
                            href = Links.KOTLIN_HOMEPAGE_URL,
                            attrs = { tw("link link-hover text-primary") },
                        ) {
                            Text(kotlin)
                        }
                        Text(and)
                        Anchor(
                            href = Links.KOBWEB_HOMEPAGE_URL,
                            attrs = { tw("link link-hover text-primary") },
                        ) {
                            Text(kobweb)
                        }
                        Text(".")
                    }
                }
            }
            Nav({ tw("grid grid-flow-col gap-4") }) {
                Anchor(
                    href = Links.GITHUB_URL,
                    attrs = { tw("link link-hover") },
                ) {
                    Text(Strings.section.footer.link.github)
                }
                Anchor(
                    href = Links.BLUESKY_URL,
                    attrs = { tw("link link-hover") },
                ) {
                    Text(Strings.section.footer.link.bluesky)
                }
                Anchor(
                    href = Links.EMAIL_URL,
                    attrs = { tw("link link-hover") },
                ) {
                    Text(Strings.section.footer.link.email)
                }
                Anchor(
                    href = Links.RSS_URL,
                    attrs = { tw("link link-hover") },
                ) {
                    Text(Strings.section.footer.link.rss)
                }
            }
        }
    }
}
