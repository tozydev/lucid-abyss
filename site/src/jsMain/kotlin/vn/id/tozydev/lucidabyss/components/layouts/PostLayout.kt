package vn.id.tozydev.lucidabyss.components.layouts

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.dom.observers.IntersectionObserver
import com.varabyte.kobweb.compose.dom.ref
import com.varabyte.kobweb.compose.dom.registerRefScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLHeadingElement
import vn.id.tozydev.lucidabyss.components.sections.post.PostHeader
import vn.id.tozydev.lucidabyss.components.sections.post.PostNavigation
import vn.id.tozydev.lucidabyss.components.sections.post.PostTableOfContents
import vn.id.tozydev.lucidabyss.components.sections.post.PostTags
import vn.id.tozydev.lucidabyss.components.widgets.JsonLdScript
import vn.id.tozydev.lucidabyss.generated.Post
import vn.id.tozydev.lucidabyss.utils.coverImagePathOrDefault
import vn.id.tozydev.lucidabyss.utils.findPost
import vn.id.tozydev.lucidabyss.utils.getHeadings
import vn.id.tozydev.lucidabyss.utils.pagefind.pagefindBody
import vn.id.tozydev.lucidabyss.utils.tw

@InitRoute
fun initPostLayout(ctx: InitRouteContext) {
    val slug = ctx.route.slug
    val post =
        requireNotNull(findPost(slug)) {
            "No post found for slug: $slug"
        }

    ctx.data.add(
        PageProperties(
            title = post.title,
            description = post.description,
        ),
    )
    ctx.data.add(post)
}

@Composable
@Layout(PAGE_LAYOUT_FNQ)
fun PostLayout(
    ctx: PageContext,
    content: @Composable () -> Unit,
) {
    val post = ctx.data.getValue<Post>()
    JsonLdScript(
        id = "post-jsonld",
        jsonLd = post.toBlogPostingJsonLd(),
    )

    Div({ tw("max-w-275 mx-auto flex flex-col lg:flex-row gap-8 items-start w-full") }) {
        var contentRef by remember { mutableStateOf<HTMLElement?>(null) }
        var headings by remember(ctx.route.path) { mutableStateOf(emptyList<HTMLHeadingElement>()) }

        // Fetch headings only once elements are added to the DOM
        registerRefScope(
            ref(contentRef, ctx.route.path) {
                headings = contentRef?.getHeadings().orEmpty()
            },
        )

        val observerOptions =
            remember {
                IntersectionObserver.Options(
                    rootMargin = "0px 0% -77%",
                    thresholds = listOf(0.0),
                )
            }

        Div({ tw("flex flex-col gap-8 flex-1 w-full min-w-0") }) {
            Article(
                {
                    tw("bg-surface-container-lowest rounded-2xl shadow-[0_20px_40px_rgba(42,40,37,0.06)] overflow-hidden w-full")
                    pagefindBody()
                },
            ) {
                Div(
                    {
                        tw("w-full h-75 md:h-125 relative")
                        attr("role", "figure")
                    },
                ) {
                    Img(
                        src = post.coverImagePathOrDefault,
                        alt = "",
                        attrs = {
                            tw("w-full h-full object-cover")
                            attr("width", "1600")
                            attr("height", "900")
                            attr("loading", "eager")
                            attr("fetchpriority", "high")
                            attr("decoding", "async")
                        },
                    )
                    Div({ tw("absolute inset-0 bg-linear-to-t from-primary/30 to-transparent") })
                }

                Div({ tw("px-5 md:px-12 lg:px-16 py-8 md:py-12") }) {
                    PostHeader(post)

                    Section(
                        Modifier
                            .tw(
                                "prose prose-blog max-w-none md:prose-lg wrap-break-word w-full overflow-hidden",
                            ).toAttrs {
                                ref {
                                    contentRef = it
                                    onDispose { }
                                }
                            },
                    ) {
                        content()
                    }

                    PostTags(post.tags)
                }
            }

            PostNavigation(post)
        }

        PostTableOfContents(
            headings = headings,
            intersectionObserverOptions = observerOptions,
        )
    }
}

private fun Post.toBlogPostingJsonLd(): String =
    """
    {
      "@context":"https://schema.org",
      "@type":"BlogPosting",
      "headline":"${title.escapeJson()}",
      "description":"${description.escapeJson()}",
      "image":"${coverImagePathOrDefault.escapeJson()}",
      "datePublished":"${publishedAt.toString().escapeJson()}",
      "author":{"@type":"Person","name":"${author.escapeJson()}"},
      "keywords":"${tags.joinToString(",").escapeJson()}"
    }
    """.trimIndent()

private fun String.escapeJson(): String =
    replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .replace("\n", "\\n")
        .replace("\r", "\\r")
        .replace("\t", "\\t")
