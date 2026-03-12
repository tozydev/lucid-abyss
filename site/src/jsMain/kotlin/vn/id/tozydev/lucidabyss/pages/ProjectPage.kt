package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.PageContext
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.ui.UnderConstruction
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SiteRoutes
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

class ProjectPage(
    language: SiteLanguage,
) : Page(language) {
    override val route =
        context(language) {
            SiteRoutes.projects
        }
    override val properties =
        strings.let {
            Properties(
                title = it.page.projects.title,
                description = it.page.projects.description,
            )
        }

    @Composable
    override fun Content(ctx: PageContext) {
        context(language) {
            Div({ tw("flex justify-center items-center flex-grow py-12") }) {
                UnderConstruction(
                    title = Strings.widget.underConstruction.title,
                    description = Strings.widget.underConstruction.description,
                )
            }
        }
    }
}
