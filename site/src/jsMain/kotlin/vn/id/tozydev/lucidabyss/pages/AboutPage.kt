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
        Properties(
            title = strings.page_about_title,
            description = strings.page_about_description,
        )

    @Composable
    override fun content(ctx: PageContext) {
        context(language, strings) {
        }
    }
}
