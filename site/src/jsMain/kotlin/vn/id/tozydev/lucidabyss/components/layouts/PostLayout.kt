package vn.id.tozydev.lucidabyss.components.layouts

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.OverflowWrap
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import com.varabyte.kobwebx.markdown.markdown
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.generated.filePathToPost
import vn.id.tozydev.lucidabyss.models.Post
import vn.id.tozydev.lucidabyss.styles.ContainerStyle
import vn.id.tozydev.lucidabyss.theme.colorScheme

val PostLayoutStyle =
    CssStyle {
    }

val ArticleStyle =
    CssStyle {
        base { Modifier.fillMaxSize().flex(1) }

        cssRule("h1") {
            Modifier
                .fontSize(3.5.cssRem)
                .fontWeight(FontWeight.Medium)
                .lineHeight(4.cssRem)
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

    Section(ContainerStyle.toAttrs()) {
        Article(ArticleStyle.toAttrs()) {
            content()
        }
    }
}
