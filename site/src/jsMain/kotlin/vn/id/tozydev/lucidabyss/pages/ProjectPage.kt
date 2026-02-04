package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.PageContext
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths
import vn.id.tozydev.lucidabyss.utils.strings

class ProjectPage(
    language: SiteLanguage,
) : Page(language) {
    override val route =
        when (language) {
            SiteLanguage.En -> SitePaths.PROJECTS_EN_PATH
            else -> SitePaths.PROJECTS_VI_PATH
        }
    override val properties =
        language.strings.let { strings ->
            Properties(
                title = strings.page_projects_title!!,
                description = strings.page_projects_description!!,
            )
        }

    @Composable
    override fun Content(ctx: PageContext) {
        context(language) {
        }
    }
}
