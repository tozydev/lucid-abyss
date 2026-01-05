package vn.id.tozydev.lucidabyss.pages.blog

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.layouts.PageLayoutData
import vn.id.tozydev.lucidabyss.components.widgets.ColumnIslandVariant
import vn.id.tozydev.lucidabyss.components.widgets.Island
import vn.id.tozydev.lucidabyss.components.widgets.PostCard
import vn.id.tozydev.lucidabyss.generated.filePathToPost
import vn.id.tozydev.lucidabyss.styles.ColorVars
import vn.id.tozydev.lucidabyss.styles.Text4XlStyle
import vn.id.tozydev.lucidabyss.styles.TextLgStyle

val BlogListStyle =
    CssStyle {
        base {
            Modifier
                .fillMaxWidth()
                .display(DisplayStyle.Grid)
                .gridTemplateColumns { repeat(1) { minmax(0.px, 1.fr) } }
                .gap(1.5.cssRem)
        }
        Breakpoint.MD {
            Modifier
                .gridTemplateColumns { repeat(2) { minmax(0.px, 1.fr) } }
        }
        Breakpoint.LG {
            Modifier
                .gridTemplateColumns { repeat(3) { minmax(0.px, 1.fr) } }
        }
    }

@InitRoute
fun initBlogPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Blog"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun BlogPage() {
    Div {
        BlogHeader(Modifier.margin(bottom = 2.cssRem))
        Div(BlogListStyle.toModifier().toAttrs()) {
            filePathToPost.values.forEach {
                PostCard(it)
            }
        }
    }
}

@Composable
private fun BlogHeader(modifier: Modifier = Modifier) {
    Island(
        modifier = Modifier.padding(3.cssRem) then modifier,
        variant = ColumnIslandVariant,
    ) {
        Div(Modifier.maxWidth(42.cssRem).toAttrs()) {
            H1(
                Text4XlStyle
                    .toModifier()
                    .fontWeight(FontWeight.Bold)
                    .color(ColorVars.TextHeading.value())
                    .margin(bottom = 1.cssRem)
                    .toAttrs(),
            ) {
                Text("Chia sẻ kiến thức")
                Br()
                SpanText("Lập trình và cuộc sống", Modifier.color(ColorVars.Primary.value()))
            }
            P(TextLgStyle.toAttrs()) {
                Text("Nơi lưu lại những bài học, kinh nghiệm và những điều thú vị trong hành trình phát triển bản thân để trở nên tốt hơn.")
            }
        }
    }
}
