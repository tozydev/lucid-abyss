package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.dom.GenericTag
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.generated.Post
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.SiteRoutes
import vn.id.tozydev.lucidabyss.utils.coverImagePathOrDefault
import vn.id.tozydev.lucidabyss.utils.formatDate
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun PostCard(
    post: Post,
    modifier: Modifier = Modifier,
) {
    Article(
        Modifier
            .tw(
                "bg-surface-container-lowest rounded-xl overflow-hidden shadow-[0_20px_40px_rgba(42,40,37,0.06)] flex flex-col group hover:-translate-y-2 transition-transform duration-300 relative",
            ).then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("flex flex-col h-full hover:text-current!") }) {
            Anchor(
                href = post.route,
                attrs = Modifier.tw("absolute inset-0 z-10").toAttrs(),
            ) {
                Span({ tw("sr-only") }) {
                    Text(Strings.widget.post.readArticle(post.title))
                }
            }
            Div({ tw("aspect-video relative overflow-hidden") }) {
                Img(
                    src = post.coverImagePathOrDefault,
                    alt = "",
                    attrs = {
                        tw("w-full h-full object-cover transition-transform duration-500 group-hover:scale-110")
                        attr("width", "720")
                        attr("height", "360")
                        attr("loading", "lazy")
                        attr("fetchpriority", "low")
                        attr("decoding", "async")
                    },
                )
            }
            Div({ tw("p-6 flex-1 flex flex-col") }) {
                Div({ tw("flex gap-2 mb-4") }) {
                    if (post.topic.isNotBlank()) {
                        Badge(
                            href = SiteRoutes.topic(post.topic),
                            text = post.topic,
                            variant = BadgeVariant.Primary,
                            size = BadgeSize.Sm,
                            modifier = Modifier.tw("relative z-20"),
                        )
                    } else {
                        Badge(
                            text = "ARTICLE",
                            variant = BadgeVariant.Primary,
                            size = BadgeSize.Sm,
                        )
                    }
                }
                H3({ tw("text-xl font-headline font-bold text-on-surface mb-3 leading-snug") }) {
                    Text(post.title)
                }
                P({ tw("text-sm text-on-surface-variant leading-relaxed mb-6") }) {
                    Text(post.description)
                }
                Div({ tw("mt-auto pt-4 flex items-center justify-between border-t border-surface-variant") }) {
                    GenericTag(
                        "time",
                        attrs =
                            Modifier
                                .tw("text-xs font-label text-on-surface-variant uppercase tracking-widest")
                                .attr("datetime", post.publishedAt.toString())
                                .toAttrs(),
                    ) {
                        Text(post.publishedAt.formatDate())
                    }
                    Span({ tw("text-sm font-headline font-bold text-primary flex items-center gap-1 hover:gap-2 transition-all") }) {
                        Text(Strings.widget.latestPost.readMore)
                        MaterialSymbol("arrow_forward", Modifier.tw("text-sm"))
                    }
                }
            }
        }
    }
}
