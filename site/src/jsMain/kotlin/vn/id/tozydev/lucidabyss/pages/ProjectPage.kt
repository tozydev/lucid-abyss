package vn.id.tozydev.lucidabyss.pages

import vn.id.tozydev.lucidabyss.strings.Strings

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.PageContext
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths

class ProjectPage(
    language: SiteLanguage,
) : Page(language) {
    override val route =
        when (language) {
            SiteLanguage.En -> SitePaths.PROJECTS_EN_PATH
            else -> SitePaths.PROJECTS_VI_PATH
        }
    override val properties =
        Strings.withLanguage(language) {
            Properties(
                title = Strings.page.projects.title,
                description = Strings.page.projects.description,
            )
        }

    @Composable
    override fun Content(ctx: PageContext) {
        context(language) {
        }
    }
}
