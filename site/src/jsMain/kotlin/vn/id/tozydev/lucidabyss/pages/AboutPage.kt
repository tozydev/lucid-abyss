package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.core.PageContext
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.about.AboutExperiences
import vn.id.tozydev.lucidabyss.components.about.AboutGallery
import vn.id.tozydev.lucidabyss.components.about.AboutMyStory
import vn.id.tozydev.lucidabyss.components.about.AboutTechStack
import vn.id.tozydev.lucidabyss.components.about.AboutTools
import vn.id.tozydev.lucidabyss.components.home.HomeSocials
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths
import vn.id.tozydev.lucidabyss.utils.tw

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
            Div({ tw("grid auto-rows-min grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-5") }) {
                AboutMyStory(Modifier.tw("col-span-1 md:col-span-2 lg:col-span-4"))

                AboutExperiences(Modifier.tw("row-span-2 md:col-span-2 lg:col-span-2"))

                AboutTechStack(Modifier.tw("col-span-1 md:col-span-2 lg:col-span-2"))

                AboutTools(Modifier.tw("col-span-1 md:col-span-2 lg:col-span-2"))

                AboutGallery(Modifier.tw("col-span-1 md:col-span-2 lg:col-span-4"))

                HomeSocials(Modifier.tw("col-span-1 md:col-span-2 lg:col-span-4"))
            }
        }
    }
}
