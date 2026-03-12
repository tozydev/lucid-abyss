package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitKobweb
import com.varabyte.kobweb.core.init.InitKobwebContext
import com.varabyte.kobweb.core.init.InitRouteContext
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.pages.blog.BlogPage
import vn.id.tozydev.lucidabyss.strings.StringsEn
import vn.id.tozydev.lucidabyss.strings.StringsVi

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
    abstract fun Content(ctx: PageContext)

    data class Properties(
        val title: String,
        val description: String,
    )
}

@InitKobweb
fun registerPages(ctx: InitKobwebContext) {
    fun register(page: Page) {
        ctx.router.register(
            route = page.route,
            layoutId = page.layout,
            initRouteMethod = page::init,
        ) { pageContext ->
            page.Content(pageContext)
        }
    }

    SiteLanguage.entries.forEach { language ->
        register(HomePage(language))
        register(AboutPage(language))
        register(ProjectPage(language))
        register(BlogPage(language))
    }
}

internal val Page.strings
    get() =
        when (language) {
            SiteLanguage.En -> StringsEn
            else -> StringsVi
        }
