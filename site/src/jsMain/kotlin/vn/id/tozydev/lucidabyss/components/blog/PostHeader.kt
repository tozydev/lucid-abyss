package vn.id.tozydev.lucidabyss.components.blog

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.dom.GenericTag
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaCalendar
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement
import vn.id.tozydev.lucidabyss.core.BlogPost
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.coverImagePathOrDefault
import vn.id.tozydev.lucidabyss.utils.formatDate
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun PostHeader(
    post: BlogPost,
    modifier: Modifier = Modifier,
) {
    Header(
        Modifier
            .tw("flex flex-col gap-8")
            .then(modifier)
            .toAttrs(),
    ) {
        Div({
            tw(
                "card card-border card-xl bg-base-100 hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] transition-all duration-300",
            )
        }) {
            Div({ tw("card-body") }) {
                Div({ tw("flex gap-2 text-sm") }) {
                    Div({ tw("badge badge-secondary") }) {
                        Text(post.topic)
                    }
                    Span { Text("|") }
                    GenericTag("time", attrsStr = "datetime=\"${post.publishedAt}\"") {
                        FaCalendar()
                        Text(" ${post.publishedAt.formatDate()}")
                    }
                }
                H1({ tw("card-title text-3xl md:text-5xl font-bold mb-2") }) {
                    Text(post.title)
                }
                P {
                    Text(post.description)
                }
            }
        }
        GenericTag<HTMLElement>(
            "figure",
            attrs = {
                tw("rounded-box shadow-md")
            },
        ) {
            Img(
                src = post.coverImagePathOrDefault,
                alt = Strings.widget.post.cover.alt(post.title),
                attrs = { tw("rounded-box object-cover") },
            )
        }
    }
}
