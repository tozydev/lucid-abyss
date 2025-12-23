package vn.id.tozydev.lucidabyss.pages.posts

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
import com.varabyte.kobweb.silk.style.selectors.descendants
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.layouts.PageLayoutData
import vn.id.tozydev.lucidabyss.components.widgets.PostCard
import vn.id.tozydev.lucidabyss.generated.posts

val PostsPageStyle =
    CssStyle {
        base {
            Modifier
                .fillMaxWidth()
                .display(DisplayStyle.Grid)
                .gap(2.cssRem)
                .gridTemplateColumns { repeat(3) { minmax(0.px, 1.fr) } }
                .gridAutoRows { minmax(5.cssRem, auto) }
        }
        descendants("*") {
            Modifier.gridArea("auto")
        }
    }

@InitRoute
fun initPostsPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Posts"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun PostsPage() {
    Div(PostsPageStyle.toModifier().toAttrs()) {
        posts.forEach {
            PostCard(it)
        }
    }
}
