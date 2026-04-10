package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.layouts.PAGE_LAYOUT_FNQ
import vn.id.tozydev.lucidabyss.components.layouts.PageProperties
import vn.id.tozydev.lucidabyss.components.sections.LatestPosts
import vn.id.tozydev.lucidabyss.components.sections.home.HomeHero
import vn.id.tozydev.lucidabyss.generated.Post
import vn.id.tozydev.lucidabyss.generated.Posts
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@InitRoute
fun initHomePage(ctx: InitRouteContext) {
    ctx.data.add(
        PageProperties(
            title = Strings.pages.home.title,
            description = Strings.pages.home.description,
        ),
    )
}

@Page
@Layout(PAGE_LAYOUT_FNQ)
@Composable
fun HomePage() {
    val latestPosts = remember { Posts.take(3) }
    HomePageContent(latestPosts)
}

@Composable
private fun HomePageContent(latestPosts: List<Post>) {
    Div {
        HomeHero(Modifier.tw("mb-24"))
        LatestPosts(posts = latestPosts)
    }
}
