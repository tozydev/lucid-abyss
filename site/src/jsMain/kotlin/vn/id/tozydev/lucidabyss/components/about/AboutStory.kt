package vn.id.tozydev.lucidabyss.components.about

import Res
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaUser
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutStory(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw("card card-border bg-base-100 hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] transition-all duration-300")
            .then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body flex flex-col md:flex-row gap-6") }) {
            // Avatar Section
            Div({ tw("flex-shrink-0 flex justify-center md:justify-start items-start") }) {
                 Img(
                    src = Res.image.avatar_96x,
                    alt = "Avatar",
                    attrs = {
                        tw("w-32 h-32 rounded-full object-cover border-4 border-base-300 shadow-md")
                    }
                )
            }

            // Content Section
            Div({ tw("flex flex-col flex-grow") }) {
                H2({ tw("card-title text-base mb-2") }) {
                    FaUser()
                    Text(Res.string.section_about_story_title)
                }
                P({ tw("text-base-content/80 whitespace-pre-line") }) {
                    Text(Res.string.section_about_story_content)
                }
            }
        }
    }
}
