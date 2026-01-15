package vn.id.tozydev.lucidabyss.components.layouts

import androidx.compose.runtime.*
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
import com.varabyte.kobwebx.markdown.markdown
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLHeadingElement
import vn.id.tozydev.lucidabyss.components.widgets.Discussion
import vn.id.tozydev.lucidabyss.components.widgets.NextPrevPosts
import vn.id.tozydev.lucidabyss.components.widgets.PostHeader
import vn.id.tozydev.lucidabyss.components.widgets.SharePost
import vn.id.tozydev.lucidabyss.components.widgets.TableOfContents
import vn.id.tozydev.lucidabyss.generated.filePathToPost
import vn.id.tozydev.lucidabyss.models.Post
import vn.id.tozydev.lucidabyss.utils.getHeadings
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

@Composable
context(ctx: PageContext)
private fun PostContent(
    post: Post,
    content: @Composable (() -> Unit),
) {
    Div({ tw("grid grid-cols-1 gap-8 lg:grid-cols-12 w-full") }) {
        var contentRef by remember { mutableStateOf<HTMLElement?>(null) }
        Aside({ tw("lg:col-span-3 lg:order-last") }) {
            Div({ tw("sticky top-16") }) {
                Div({ tw("card card-border bg-base-100 lg:mb-8") }) {
                    Div({ tw("card-body") }) {
                        var hierarchy by remember(ctx.route.path) { mutableStateOf(emptyList<HTMLHeadingElement>()) }
                        // Fetch headings only once elements are added to the DOM
                        registerRefScope(
                            ref(contentRef, ctx.route.path) {
                                hierarchy = contentRef?.getHeadings().orEmpty()
                            },
                        )

                        H3({ tw("card-title text-sm uppercase") }) {
                            FaList()
                            Text("Mục lục")
                        }

                        TableOfContents(
                            headings = hierarchy,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }

                SharePost(Modifier.tw("hidden lg:block"))
            }
        }
        Div({ tw("lg:col-span-9 flex flex-col gap-8") }) {
            Div({ tw("rounded-box bg-base-100 p-10") }) {
                Article(
                    Modifier
                        .tw("prose dark:prose-invert md:prose-lg max-w-none prose-code:wrap-break-word")
                        .toAttrs {
                            ref {
                                contentRef = it
                                onDispose { }
                            }
                        },
                ) {
                    content()
                }
            }

            SharePost()

            NextPrevPosts(post)

            Discussion()
        }
    }
}
