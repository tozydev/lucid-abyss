package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.Badge
import vn.id.tozydev.lucidabyss.components.widgets.BadgeSize
import vn.id.tozydev.lucidabyss.components.widgets.BadgeVariant
import vn.id.tozydev.lucidabyss.components.widgets.MaterialSymbol
import vn.id.tozydev.lucidabyss.core.BlogPost
import vn.id.tozydev.lucidabyss.core.SiteRoutes
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.formatDate
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun PostHeader(
    post: BlogPost,
    modifier: Modifier = Modifier,
) {
    Header(
        Modifier
            .tw("mb-8 md:mb-12")
            .then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("flex items-center flex-wrap gap-y-4 gap-x-6 mb-6 md:mb-8") }) {
            Badge(
                text = post.topic,
                href = SiteRoutes.topic(post.topic),
                variant = BadgeVariant.Primary,
                size = BadgeSize.Md,
                modifier = Modifier.tw("rounded-md"),
            )
            Div({ tw("flex items-center gap-6") }) {
                Div({ tw("flex items-center gap-2 text-on-surface") }) {
                    MaterialSymbol("calendar_today", Modifier.tw("text-lg leading-none text-primary"))
                    Span({ tw("font-mono text-[13px] font-semibold uppercase tracking-wider") }) {
                        Text(post.publishedAt.formatDate())
                    }
                }
            }
        }

        H1(
            {
                tw(
                    "text-3xl md:text-4xl lg:text-5xl font-headline font-extrabold text-on-surface leading-tight mb-8 wrap-break-word break-all md:wrap-break-word",
                )
            },
        ) {
            Text(post.title)
        }

        Div({ tw("flex items-center gap-4 py-6 border-t border-surface-variant") }) {
            Div({ tw("w-12 h-12 rounded-full bg-surface-container-highest overflow-hidden") }) {
                Img(
                    src = "/images/avatar_96x.webp",
                    alt = Strings.page.about.profileImageAlt,
                    attrs = { tw("w-full h-full object-cover") },
                )
            }
            Div {
                P({ tw("font-headline font-bold text-on-surface") }) {
                    Text(Strings.page.blog.author.name)
                }
                P({ tw("font-label text-xs text-outline uppercase tracking-wider") }) {
                    Text(Strings.page.blog.author.role)
                }
            }
        }
    }
}
