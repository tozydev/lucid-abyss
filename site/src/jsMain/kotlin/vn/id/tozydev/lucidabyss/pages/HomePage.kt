package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.core.PageContext
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.sections.Hero
import vn.id.tozydev.lucidabyss.components.sections.Socials
import vn.id.tozydev.lucidabyss.components.sections.TechStack
import vn.id.tozydev.lucidabyss.components.widgets.FeaturedProject
import vn.id.tozydev.lucidabyss.components.widgets.LatestPost
import vn.id.tozydev.lucidabyss.components.widgets.Location
import vn.id.tozydev.lucidabyss.components.widgets.Quote
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths
import vn.id.tozydev.lucidabyss.generated.BlogPosts
import vn.id.tozydev.lucidabyss.utils.tw

class HomePage(
    language: SiteLanguage,
) : Page(language) {
    override val properties =
        Properties(
            title = strings.page_home_title,
            description = strings.page_home_description,
        )

    override val route = SitePaths.HOME_PATH

    @Composable
    override fun content(ctx: PageContext) {
        context(language, strings) {
            Div({ tw("grid auto-rows-min grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-5") }) {
                Hero(
                    Modifier.tw("col-span-1 row-span-2 md:col-span-2"),
                )

                Quote()

                Location()

                LatestPost(
                    post = BlogPosts[language]!!.first(),
                    modifier = Modifier.tw("md:col-span-2"),
                )

                FeaturedProject(Modifier.tw("row-span-2 md:col-span-2"))

                TechStack(Modifier.tw("md:col-span-2"))

                Socials(Modifier.tw("md:col-span-2"))
            }
        }
    }
}
