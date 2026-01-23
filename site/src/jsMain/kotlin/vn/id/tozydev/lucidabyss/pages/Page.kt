package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitKobweb
import com.varabyte.kobweb.core.init.InitKobwebContext
import com.varabyte.kobweb.core.init.InitRouteContext
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.pages.blog.BlogPage

abstract class Page(
    val language: SiteLanguage,
) {
    open val layout = "vn.id.tozydev.lucidabyss.layouts.PageLayout"

    abstract val route: String

    abstract val properties: Properties

    fun init(ctx: InitRouteContext) {
        ctx.data.add(properties)
        ctx.data.add(language)
        additionalInit(ctx)
    }

    protected open fun additionalInit(ctx: InitRouteContext) = Unit

    @Composable
    abstract fun content(ctx: PageContext)

    data class Properties(
        val title: String,
        val description: String,
    )
}

@InitKobweb
fun registerPages(ctx: InitKobwebContext) {
    SiteLanguage.entries
        .flatMap {
            listOf(
                HomePage(it),
                AboutPage(it),
                ProductPage(it),
                BlogPage(it),
            )
        }.forEach { page ->
            ctx.router.register(
                route = page.actualRoute,
                layoutId = page.layout,
                initRouteMethod = page::init,
            ) { ctx ->
                page.content(ctx)
            }
        }
}

private val Page.actualRoute
    get() =
        when (language) {
            SiteLanguage.Default -> route
            else -> "/${language.code}$route"
        }
