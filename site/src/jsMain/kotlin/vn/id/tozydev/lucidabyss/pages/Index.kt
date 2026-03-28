package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import vn.id.tozydev.lucidabyss.components.layouts.PAGE_LAYOUT_FNQ
import vn.id.tozydev.lucidabyss.components.layouts.PageProperties
import vn.id.tozydev.lucidabyss.components.sections.FeaturedPosts
import vn.id.tozydev.lucidabyss.components.sections.HomeHero
import vn.id.tozydev.lucidabyss.generated.Posts
import vn.id.tozydev.lucidabyss.strings.Strings

@InitRoute
fun initIndexPage(ctx: InitRouteContext) {
    ctx.data.add(
        PageProperties(
            title = Strings.page.home.title,
            description = Strings.page.home.description,
        ),
    )
}

@Page
@Layout(PAGE_LAYOUT_FNQ)
@Composable
fun IndexPage() {
    HomeHero()

    FeaturedPosts(
        posts = Posts.take(3),
    )
}
