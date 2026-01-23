package vn.id.tozydev.lucidabyss.components

import Res
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
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
                P {
                    Text(Res.string.section_footer_copyright)
                    Text(Res.string.section_footer_built_with)
                }
                P {
                    Text(Res.string.section_footer_rights)
                }
            }
            Nav({ tw("grid grid-flow-col gap-4") }) {
                Anchor(
                    href = "",
                    attrs = { tw("link link-hover") },
                ) {
                    Text(Res.string.section_footer_link_github)
                }
                Anchor(
                    href = "",
                    attrs = { tw("link link-hover") },
                ) {
                    Text(Res.string.section_footer_link_bluesky)
                }
                Anchor(
                    href = "",
                    attrs = { tw("link link-hover") },
                ) {
                    Text(Res.string.section_footer_link_email)
                }
                Anchor(
                    href = "",
                    attrs = { tw("link link-hover") },
                ) {
                    Text(Res.string.section_footer_link_rss)
                }
            }
        }
    }
}
