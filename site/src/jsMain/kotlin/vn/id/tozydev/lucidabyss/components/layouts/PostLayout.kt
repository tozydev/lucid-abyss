package vn.id.tozydev.lucidabyss.components.layouts

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.OverflowWrap
import com.varabyte.kobweb.compose.css.TextTransform
import com.varabyte.kobweb.compose.dom.ref
import com.varabyte.kobweb.compose.dom.registerRefScope
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.graphics.ImageDecoding
import com.varabyte.kobweb.silk.components.graphics.ImageLoading
import com.varabyte.kobweb.silk.components.icons.mdi.IconStyle
import com.varabyte.kobweb.silk.components.icons.mdi.MdiToc
import com.varabyte.kobweb.silk.components.layout.HorizontalDivider
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import com.varabyte.kobwebx.markdown.markdown
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement
import vn.id.tozydev.lucidabyss.components.elements.Time
import vn.id.tozydev.lucidabyss.components.widgets.HeadingItem
import vn.id.tozydev.lucidabyss.components.widgets.TableOfContents
import vn.id.tozydev.lucidabyss.components.widgets.getHeadingHierarchy
import vn.id.tozydev.lucidabyss.generated.filePathToPost
import vn.id.tozydev.lucidabyss.models.Post
import vn.id.tozydev.lucidabyss.models.authorAvatarUrl
import vn.id.tozydev.lucidabyss.models.authorWebsite
import vn.id.tozydev.lucidabyss.models.coverImagePathOrDefault
import vn.id.tozydev.lucidabyss.styles.ContainerStyle
import vn.id.tozydev.lucidabyss.styles.TypeDisplayModifier
import vn.id.tozydev.lucidabyss.styles.TypeLabelStyle
import vn.id.tozydev.lucidabyss.theme.colorScheme
import vn.id.tozydev.lucidabyss.theme.toColorScheme
import vn.id.tozydev.lucidabyss.utils.formatDate

val ArticleStyle =
    CssStyle {
        cssRule("p") {
            Modifier
                .margin(bottom = 1.cssRem)
        }

        cssRule("h1") {
            TypeDisplayModifier
                .margin(bottom = 1.cssRem)
        }

        cssRule("h2") {
            Modifier
                .fontSize(2.cssRem)
                .fontWeight(FontWeight.Medium)
                .lineHeight(2.5.cssRem)
                .margin(top = 2.75.cssRem, bottom = 1.5.cssRem)
        }

        cssRule("h3") {
            Modifier
                .fontSize(1.75.cssRem)
                .fontWeight(FontWeight.Normal)
                .lineHeight(2.25.cssRem)
                .margin(top = 2.25.cssRem, bottom = 1.25.cssRem)
        }

        cssRule("h4") {
            Modifier
                .fontSize(1.25.cssRem)
                .fontWeight(FontWeight.Normal)
                .lineHeight(2.cssRem)
                .margin(topBottom = 1.125.cssRem)
        }

        cssRule("ul, ol") {
            Modifier
                .fillMaxWidth()
                .padding(left = 1.5.cssRem)
                .overflowWrap(OverflowWrap.BreakWord)
        }

        cssRule(" :is(li,ol,ul)") {
            Modifier.margin(bottom = 0.5.cssRem)
        }

        cssRule("code") {
            Modifier
                .color(
                    colorMode
                        .toPalette()
                        .color
                        .toRgb()
                        .copyf(alpha = 0.8f),
                ).fontWeight(FontWeight.Bolder)
        }

        cssRule("pre") {
            Modifier
                .margin(top = 0.5.cssRem, bottom = 2.cssRem)
                .fillMaxWidth()
        }
        cssRule("pre > code") {
            Modifier
                .display(DisplayStyle.Block)
                .fillMaxWidth()
                .backgroundColor(colorScheme.surfaceContainerHighest)
                .border(1.px, LineStyle.Solid, colorScheme.outline)
                .borderRadius(0.25.cssRem)
                .padding(0.5.cssRem)
                .fontSize(1.cssRem)
                .overflow { x(Overflow.Auto) }
        }
    }

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
    val colorScheme = ColorMode.current.toColorScheme()

    Column(
        Modifier
            .fillMaxWidth()
            .gap(1.cssRem),
    ) {
        Header(
            Modifier
                .fillMaxWidth()
                .display(DisplayStyle.Grid)
                .gridTemplateColumns { repeat(2) { minmax(0.px, 1.fr) } }
                .gridAutoFlow(GridAutoFlow.Row)
                .gap(0.5.cssRem)
                .toAttrs(),
        ) {
            Div(
                ContainerStyle
                    .toModifier()
                    .fillMaxWidth()
                    .display(DisplayStyle.Flex)
                    .flexDirection(FlexDirection.Column)
                    .justifyContent(JustifyContent.Center)
                    .padding(3.5.cssRem)
                    .toAttrs(),
            ) {
                H1 {
                    Text(post.title)
                }
                P(
                    Modifier
                        .color(colorScheme.onSurfaceVariant)
                        .fontWeight(FontWeight.Medium)
                        .toAttrs(),
                ) {
                    Text(post.description)
                }

                HorizontalDivider()

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .gap(1.cssRem),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        src = post.authorAvatarUrl,
                        alt = "Avatar of ${post.author}",
                        modifier =
                            Modifier
                                .size(3.cssRem)
                                .borderRadius(50.percent),
                        loading = ImageLoading.Lazy,
                        decoding = ImageDecoding.Async,
                    )
                    Column(
                        modifier =
                            Modifier
                                .flexGrow(1)
                                .gap(0.325.cssRem),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Span(TypeLabelStyle.toAttrs()) {
                            Text("By ")
                            Link(post.authorWebsite) {
                                Text(post.author)
                            }
                        }
                        Time(
                            datetime = post.publishedAt.toString(),
                            attrs = TypeLabelStyle.toAttrs(),
                        ) {
                            Text(post.publishedAt.formatDate())
                        }
                    }
                }
            }

            Image(
                src = post.coverImagePathOrDefault,
                alt = "Cover image for ${post.title}",
                modifier =
                    Modifier
                        .borderRadius(1.5.cssRem)
                        .objectFit(ObjectFit.Cover)
                        .maxWidth(100.percent)
                        .maxHeight(630.px),
                loading = ImageLoading.Lazy,
                decoding = ImageDecoding.Async,
            )
        }

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .gap(1.cssRem),
        ) {
            var contentRef by remember { mutableStateOf<HTMLElement?>(null) }
            Article(
                ArticleStyle
                    .toModifier()
                    .then(ContainerStyle.toModifier())
                    .flex(1)
                    .padding(3.5.cssRem)
                    .toAttrs {
                        ref {
                            contentRef = it
                            onDispose { }
                        }
                    },
            ) {
                content()
            }
            Aside(
                Modifier
                    .position(Position.Sticky)
                    .top(1.cssRem)
                    .toAttrs(),
            ) {
                Section(
                    ContainerStyle
                        .toModifier()
                        .display(DisplayStyle.Flex)
                        .flexDirection(FlexDirection.Column)
                        .gap(1.cssRem)
                        .width(20.cssRem)
                        .toAttrs(),
                ) {
                    var hierarchy by remember(ctx.route.path) { mutableStateOf(emptyList<HeadingItem>()) }
                    // Fetch headings only once elements are added to the DOM
                    registerRefScope(
                        ref(contentRef, ctx.route.path) {
                            hierarchy = contentRef?.getHeadingHierarchy().orEmpty()
                        },
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth().gap(0.5.cssRem),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        MdiToc(style = IconStyle.ROUNDED)
                        SpanText(
                            "Mục lục",
                            modifier =
                                TypeLabelStyle
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
            }
        }
    }
}
