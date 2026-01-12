package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.layouts.PageLayoutData
import vn.id.tozydev.lucidabyss.components.sections.Hero
import vn.id.tozydev.lucidabyss.components.widgets.Location
import vn.id.tozydev.lucidabyss.components.widgets.Quote

val HomePageStyle =
    CssStyle {
        base {
            Modifier
                .display(DisplayStyle.Grid)
                .gridTemplateColumns { repeat(1) { minmax(0.px, 1.fr) } }
                .gridAutoRows { size(minContent) }
                .gridTemplateAreas(
                    "hero",
                    "quote",
                    "location",
                    "latest-post",
                    "featured-project",
                    "tech-stack",
                    "socials",
                ).gap(1.cssRem)
        }

        Breakpoint.MD {
            Modifier
                .gridTemplateColumns { repeat(2) { minmax(0.px, 1.fr) } }
                .gridTemplateAreas(
                    "hero hero",
                    "quote location",
                    "latest-post latest-post",
                    "featured-project featured-project",
                    "tech-stack tech-stack",
                    "socials socials",
                )
        }
        Breakpoint.LG {
            Modifier
                .gridTemplateColumns { repeat(4) { minmax(0.px, 1.fr) } }
                .gridTemplateAreas(
                    "hero hero quote location",
                    "hero hero latest-post latest-post",
                    "featured-project featured-project tech-stack tech-stack",
                    "featured-project featured-project socials socials",
                )
        }
    }

@InitRoute
fun initHomePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Home"))
}

@Page
@Composable
fun HomePage() {
    Div({}) {
        Hero()

        Quote()

        Location()
    }
    /* Div(HomePageStyle.toModifier().toAttrs()) {
        Hero(Modifier.gridArea("hero"))

        Quote(Modifier.gridArea("quote"))

        Location(Modifier.gridArea("location"))

        LatestPost(
            filePathToPost.values.first(),
            Modifier.gridArea("latest-post"),
        )

        FeaturedProject(Modifier.gridArea("featured-project"))

        TechStack(Modifier.gridArea("tech-stack"))

        Socials(Modifier.gridArea("socials"))
    } */
}
