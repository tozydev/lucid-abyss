package vn.id.tozydev.lucidabyss.components.layouts

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextTransform
import com.varabyte.kobweb.compose.dom.ref
import com.varabyte.kobweb.compose.dom.registerRefScope
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.icons.fa.FaList
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobwebx.markdown.markdown
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement
import vn.id.tozydev.lucidabyss.components.sections.Discussion
import vn.id.tozydev.lucidabyss.components.sections.PostHeader
import vn.id.tozydev.lucidabyss.components.widgets.ColumnIslandVariant
import vn.id.tozydev.lucidabyss.components.widgets.HeadingItem
import vn.id.tozydev.lucidabyss.components.widgets.Island
import vn.id.tozydev.lucidabyss.components.widgets.IslandStyle
import vn.id.tozydev.lucidabyss.components.widgets.NextPrevPosts
import vn.id.tozydev.lucidabyss.components.widgets.SharePost
import vn.id.tozydev.lucidabyss.components.widgets.TableOfContents
import vn.id.tozydev.lucidabyss.components.widgets.getHeadingHierarchy
import vn.id.tozydev.lucidabyss.generated.filePathToPost
import vn.id.tozydev.lucidabyss.models.Post
import vn.id.tozydev.lucidabyss.styles.TextSmStyle
import vn.id.tozydev.lucidabyss.utils.tw

@InitRoute
fun initPostLayout(ctx: InitRouteContext) {
    val post =
        requireNotNull(filePathToPost[ctx.markdown?.path]) {
            "No post metadata found for path: ${ctx.markdown?.path}"
        }

    ctx.data.add(PageLayoutData(post.title))
    ctx.data.add(post)
}

@Composable
@Layout(".components.layouts.PageLayout")
fun PostLayout(
    ctx: PageContext,
    content: @Composable () -> Unit,
) {
    val post = ctx.data.getValue<Post>()

    Column(
        Modifier
            .fillMaxWidth()
            .gap(2.cssRem),
    ) {
        PostHeader(post, Modifier.fillMaxWidth())

        context(ctx) {
            PostContent(post, content)
        }
    }
}

val PostLayoutContentStyle =
    CssStyle {
        base {
            Modifier
                .fillMaxWidth()
                .display(DisplayStyle.Grid)
                .gridTemplateColumns { repeat(1) { minmax(0.px, 1.fr) } }
                .gap(2.cssRem)
        }

        Breakpoint.LG {
            Modifier
                .gridTemplateColumns { repeat(12) { minmax(0.px, 1.fr) } }
        }
    }

val PostLayoutTocStyle =
    CssStyle {
        Breakpoint.LG {
            Modifier
                .gridColumn("span 3", "span 3")
                .order(9999)
        }
    }

val ArticleContainerStyle =
    CssStyle {
        Breakpoint.LG {
            Modifier
                .gridColumn("span 9", "span 9")
        }
    }

val BlogArticleStyle =
    CssStyle({ IslandStyle.toModifier() }) {
        base {
            Modifier.padding(3.cssRem)
        }
    }

@Composable
context(ctx: PageContext)
private fun PostContent(
    post: Post,
    content: @Composable (() -> Unit),
) {
    Div(PostLayoutContentStyle.toAttrs()) {
        var contentRef by remember { mutableStateOf<HTMLElement?>(null) }
        Aside(PostLayoutTocStyle.toAttrs()) {
            Div(
                Modifier
                    .position(Position.Sticky)
                    .top(3.cssRem)
                    .toAttrs(),
            ) {
                Island(
                    modifier = Modifier.gap(1.cssRem).margin(bottom = 1.cssRem),
                    variant = ColumnIslandVariant,
                ) {
                    var hierarchy by remember(ctx.route.path) { mutableStateOf(emptyList<HeadingItem>()) }
                    // Fetch headings only once elements are added to the DOM
                    registerRefScope(
                        ref(contentRef, ctx.route.path) {
                            hierarchy = contentRef?.getHeadingHierarchy().orEmpty()
                        },
                    )

                    H3(
                        Modifier
                            .display(DisplayStyle.Flex)
                            .alignItems(AlignItems.Center)
                            .gap(0.5.cssRem)
                            .toAttrs(),
                    ) {
                        FaList()
                        SpanText(
                            "Mục lục",
                            modifier =
                                TextSmStyle
                                    .toModifier()
                                    .textTransform(TextTransform.Uppercase)
                                    .fontWeight(FontWeight.Bold),
                        )
                    }

                    TableOfContents(
                        hierarchy = hierarchy,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

                SharePost()
            }
        }
        Div(ArticleContainerStyle.toAttrs()) {
            Article(
                BlogArticleStyle
                    .toModifier()
                    .tw("prose dark:prose-invert lg:prose-xl max-w-none")
                    .toAttrs {
                        ref {
                            contentRef = it
                            onDispose { }
                        }
                    },
            ) {
                content()
            }

            NextPrevPosts(post, Modifier.margin(top = 2.cssRem))

            Discussion(Modifier.margin(top = 2.cssRem))
        }
    }
}
