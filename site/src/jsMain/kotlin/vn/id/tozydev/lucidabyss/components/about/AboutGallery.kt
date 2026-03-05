package vn.id.tozydev.lucidabyss.components.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaImages
import kotlinx.browser.document
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

    fun scrollToSlide(id: String) {
        document.getElementById(id)?.scrollIntoView(
            js("{behavior: 'smooth', block: 'nearest', inline: 'start'}"),
        )
    }

    Div(
        Modifier
            .tw(
                "card card-border bg-base-100 card-lg hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] transition-all duration-300",
            ).then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body") }) {
            H2({ tw("card-title text-base") }) {
                FaImages()
                Text(Strings.widget.about.gallery.title)
            }

            Div({ tw("carousel carousel-center rounded-box w-full space-x-4 p-4") }) {
                images.forEachIndexed { index, url ->
                    val id = "slide${index + 1}"
                    Div(
                        {
                            tw("carousel-item relative w-full")
                            id(id)
                        },
                    ) {
                        Img(
                            src = url,
                            alt = "Gallery Image",
                            attrs = { tw("w-full rounded-box h-48 md:h-64 object-cover") },
                        )
                        Div({ tw("absolute left-5 right-5 top-1/2 flex -translate-y-1/2 transform justify-between") }) {
                            val prevIndex = if (index == 0) images.size else index
                            val nextIndex = if (index == images.size - 1) 1 else index + 2
                            Button(
                                attrs = {
                                    tw("btn btn-circle btn-sm bg-base-100/50 hover:bg-base-100 border-none")
                                    onClick { scrollToSlide("slide$prevIndex") }
                                },
                            ) { Text("❮") }
                            Button(
                                attrs = {
                                    tw("btn btn-circle btn-sm bg-base-100/50 hover:bg-base-100 border-none")
                                    onClick { scrollToSlide("slide$nextIndex") }
                                },
                            ) { Text("❯") }
                        }
                    }
                }
            }
        }
    }
}
