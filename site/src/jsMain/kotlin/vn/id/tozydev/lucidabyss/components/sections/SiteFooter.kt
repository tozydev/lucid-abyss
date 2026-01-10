package vn.id.tozydev.lucidabyss.components.sections

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
                    Text("© 2026 tozydev. ")
                    Text("Built with Kobweb.")
                }
                P {
                    Text("All rights reserved")
                }
            }
            Nav({ tw("grid grid-flow-col gap-4") }) {
                Anchor(
                    href = "",
                    attrs = { tw("link link-hover") },
                ) {
                    Text("GitHub")
                }
                Anchor(
                    href = "",
                    attrs = { tw("link link-hover") },
                ) {
                    Text("Bluesky")
                }
                Anchor(
                    href = "",
                    attrs = { tw("link link-hover") },
                ) {
                    Text("Email")
                }
                Anchor(
                    href = "",
                    attrs = { tw("link link-hover") },
                ) {
                    Text("RSS")
                }
            }
        }
    }
}
