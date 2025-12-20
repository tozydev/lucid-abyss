package vn.id.tozydev.lucidabyss.pages.posts

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import vn.id.tozydev.lucidabyss.components.layouts.PageLayoutData

@InitRoute
fun initPostsPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Posts"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun PostsPage() {
}
