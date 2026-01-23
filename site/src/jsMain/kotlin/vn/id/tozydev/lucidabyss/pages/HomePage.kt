package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.core.PageContext
import org.jetbrains.compose.web.dom.*
import strings.StringsEn
import strings.StringsVi
import vn.id.tozydev.lucidabyss.components.FeaturedProject
import vn.id.tozydev.lucidabyss.components.Hero
import vn.id.tozydev.lucidabyss.components.LatestPost
import vn.id.tozydev.lucidabyss.components.Location
import vn.id.tozydev.lucidabyss.components.Quote
import vn.id.tozydev.lucidabyss.components.Socials
import vn.id.tozydev.lucidabyss.components.TechStack
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths
import vn.id.tozydev.lucidabyss.generated.BlogPosts
import vn.id.tozydev.lucidabyss.utils.tw

class HomePage(
    language: SiteLanguage,
) : Page(language) {
    override val properties =
        when (language) {
            SiteLanguage.En -> {
                Properties(
                    title = StringsEn.page_home_title,
                    description = StringsEn.page_home_description,
                )
            }

            else -> {
                Properties(
                    title = StringsVi.page_home_title,
                    description = StringsVi.page_home_description,
                )
            }
        }

    override val route = SitePaths.HOME_PATH

    @Composable
    override fun content(ctx: PageContext) {
        context(language) {
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
