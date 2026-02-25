package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.PageContext
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths

class AboutPage(
    language: SiteLanguage,
) : Page(language) {
    override val route = SitePaths.ABOUT_PATH
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
