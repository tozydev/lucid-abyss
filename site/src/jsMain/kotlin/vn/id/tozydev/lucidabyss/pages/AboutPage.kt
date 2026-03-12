package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.PageContext
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SiteRoutes

class AboutPage(
    language: SiteLanguage,
) : Page(language) {
    override val route =
        context(language) {
            SiteRoutes.about
        }
    override val properties =
        strings.let {
            Properties(
                title = it.page.about.title,
                description = it.page.about.description,
            )
        }

    @Composable
    override fun Content(ctx: PageContext) {
        context(language) {
        }
    }
}
