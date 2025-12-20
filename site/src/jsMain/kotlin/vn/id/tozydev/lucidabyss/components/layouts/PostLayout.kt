package vn.id.tozydev.lucidabyss.components.layouts

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.OverflowWrap
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import com.varabyte.kobwebx.markdown.markdown
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.Container
import vn.id.tozydev.lucidabyss.components.widgets.NoScaleOnHoverContainerVariant
import vn.id.tozydev.lucidabyss.models.PageLayoutData

val PostStyle =
    CssStyle {
        base { Modifier.fillMaxSize() }

        cssRule("h1") {
            Modifier
                .fontSize(3.cssRem)
                .fontWeight(400)
                .margin(bottom = 2.5.cssRem)
                .lineHeight(1.2) // 1.5x doesn't look as good on very large text
        }

        cssRule("h2") {
            Modifier
                .fontSize(3.cssRem)
                .fontWeight(300)
                .margin(topBottom = 2.cssRem)
        }

        cssRule("h3") {
            Modifier
                .fontSize(2.4.cssRem)
                .fontWeight(300)
                .margin(topBottom = 1.5.cssRem)
        }

        cssRule("h4") {
            Modifier
                .fontSize(1.2.cssRem)
                .fontWeight(FontWeight.Bolder)
                .margin(top = 1.cssRem, bottom = 0.5.cssRem)
        }

        cssRule("ul") {
            Modifier.fillMaxWidth().overflowWrap(OverflowWrap.BreakWord)
        }

        cssRule(" :is(li,ol,ul)") {
            Modifier.margin(bottom = 0.25.cssRem)
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
                .backgroundColor(Colors.WhiteSmoke)
                .border(1.px, LineStyle.Solid, colorMode.toPalette().color)
                .borderRadius(0.25.cssRem)
                .padding(0.5.cssRem)
                .fontSize(1.cssRem)
                .overflow { x(Overflow.Auto) }
        }
    }

@InitRoute
fun initMarkdownLayout(ctx: InitRouteContext) {
    val title = ctx.markdown!!.frontMatter["title"]?.singleOrNull()
    require(title != null) { "Markdown file must set \"title\" in frontmatter" }

    ctx.data.add(PageLayoutData(title))
}

@Composable
@Layout(".components.layouts.PageLayout")
fun PostLayout(content: @Composable () -> Unit) {
    Container(
        modifier = PostStyle.toModifier(),
        variant = NoScaleOnHoverContainerVariant,
    ) {
        Article {
            content()
        }
    }
}
