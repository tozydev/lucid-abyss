package vn.id.tozydev.lucidabyss.components.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaUser
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutMyStory(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw(
                "card card-border bg-base-100 card-lg hover-primary-glow",
            ).then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body flex-col md:flex-row gap-6 items-center md:items-start") }) {
            Img(
                src = "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?auto=format&fit=crop&w=300&q=80",
                alt = "Avatar",
                attrs = { tw("rounded-full w-32 h-32 md:w-48 md:h-48 object-cover border-4 border-base-300 shadow-md shrink-0") },
            )
            Div({ tw("flex flex-col gap-4 text-center md:text-left") }) {
                H2({ tw("card-title text-2xl font-bold justify-center md:justify-start") }) {
                    FaUser()
                    Text(Strings.widget.about.mystory.title)
                }
                P({ tw("text-base-content/80 leading-relaxed text-sm md:text-base") }) {
                    Text(Strings.widget.about.mystory.description)
                }
            }
        }
    }
}
