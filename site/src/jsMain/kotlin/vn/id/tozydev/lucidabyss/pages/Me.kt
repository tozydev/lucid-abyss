package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import vn.id.tozydev.lucidabyss.components.layouts.PageLayoutData

@InitRoute
fun initMePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Về tôi"))
}

@Page
@Composable
fun MePage() {
}
