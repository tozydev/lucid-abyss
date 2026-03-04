package vn.id.tozydev.lucidabyss.components.about

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaImages
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutGallery(modifier: Modifier = Modifier) {
    val images =
        listOf(
            "https://images.unsplash.com/photo-1542831371-29b0f74f9713?auto=format&fit=crop&w=600&q=80",
            "https://images.unsplash.com/photo-1498050108023-c5249f4df085?auto=format&fit=crop&w=600&q=80",
            "https://images.unsplash.com/photo-1517694712202-14dd9538aa97?auto=format&fit=crop&w=600&q=80",
            "https://images.unsplash.com/photo-1555066931-4365d14bab8c?auto=format&fit=crop&w=600&q=80",
        )

    Div(
        Modifier
            .tw(
                "card card-border bg-base-100 card-lg hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] transition-all duration-300",
            ).then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body") }) {
            H2({ tw("card-title text-base mb-4") }) {
                FaImages()
                Text(Strings.widget.about.gallery.title)
            }

            // DaisyUI Carousel
            Div({ tw("carousel carousel-center rounded-box w-full space-x-4 p-4") }) {
                images.forEach { url ->
                    Div({ tw("carousel-item") }) {
                        Img(
                            src = url,
                            alt = "Gallery Image",
                            attrs = { tw("rounded-box h-48 md:h-64 object-cover") },
                        )
                    }
                }
            }
        }
    }
}
