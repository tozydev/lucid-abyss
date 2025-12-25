package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.layouts.PageLayoutData
import vn.id.tozydev.lucidabyss.components.sections.Hero

val HomePageStyle =
    CssStyle.base {
        Modifier
            .fillMaxSize()
            .display(DisplayStyle.Grid)
            .gridTemplateColumns { repeat(4) { minmax(0.px, 1.fr) } }
            .gridAutoRows { minmax(10.cssRem, auto) }
            .gap(1.cssRem)
            .gridAutoFlow(GridAutoFlow.Row)
            .gridTemplateAreas(
                "hero hero hero .",
            )
    }

@InitRoute
fun initHomePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Home"))
}

@Page
@Composable
fun HomePage() {
    Div(HomePageStyle.toModifier().toAttrs()) {
        Hero(
            Modifier
                .fillMaxSize()
                .gridArea("hero"),
        )
    }
}
