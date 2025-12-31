package vn.id.tozydev.lucidabyss.pages.blog

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.cssRule
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.layouts.PageLayoutData
import vn.id.tozydev.lucidabyss.components.widgets.BlogFilters
import vn.id.tozydev.lucidabyss.components.widgets.ColumnIslandVariant
import vn.id.tozydev.lucidabyss.components.widgets.Island
import vn.id.tozydev.lucidabyss.components.widgets.Pagination
import vn.id.tozydev.lucidabyss.components.widgets.PostCard
import vn.id.tozydev.lucidabyss.components.widgets.PostCardStyle
import vn.id.tozydev.lucidabyss.generated.filePathToPost

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

val FeaturedPostCardVariant =
    PostCardStyle.addVariant {
        Breakpoint.MD {
            Modifier
                .gridColumn("span 2")
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Row)
        }

        cssRule(Breakpoint.MD, "> :first-child") {
            Modifier
                .fillMaxHeight()
                .width(50.percent)
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
        PageHeader(Modifier.margin(bottom = 1.cssRem))
        BlogFilters(Modifier.margin(bottom = 2.cssRem))
        Div(BlogListStyle.toModifier().toAttrs()) {
            PostCard(
                post = filePathToPost.values.first(),
                variant = FeaturedPostCardVariant,
            )
            filePathToPost.values.forEach {
                PostCard(it)
            }
        }

        Div(
            Modifier
                .display(DisplayStyle.Flex)
                .justifyContent(JustifyContent.Center)
                .margin(top = 1.cssRem)
                .toAttrs(),
        ) {
            Pagination()
        }
    }
}

@Composable
private fun PageHeader(modifier: Modifier = Modifier) {
    Island(
        modifier = modifier,
        variant = ColumnIslandVariant,
    ) {
        H1 {
            Text("Chia sẻ trải nghiệm")
            Br()
            Text("Lập trình và cuộc sống")
        }
        P {
            Text("Nơi lưu lại những bài học, kinh nghiệm và những điều thú vị của tôi trong cuộc sống và công việc.")
        }
    }
}
