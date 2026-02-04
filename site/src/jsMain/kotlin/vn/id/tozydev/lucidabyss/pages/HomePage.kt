package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.core.PageContext
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.home.HomeFeaturedProject
import vn.id.tozydev.lucidabyss.components.home.HomeHero
import vn.id.tozydev.lucidabyss.components.home.HomeLatestPost
import vn.id.tozydev.lucidabyss.components.home.HomeLocation
import vn.id.tozydev.lucidabyss.components.home.HomeQuote
import vn.id.tozydev.lucidabyss.components.home.HomeSocials
import vn.id.tozydev.lucidabyss.components.home.HomeTechStack
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths
import vn.id.tozydev.lucidabyss.generated.BlogPosts
import vn.id.tozydev.lucidabyss.utils.strings
import vn.id.tozydev.lucidabyss.utils.tw

class HomePage(
    language: SiteLanguage,
) : Page(language) {
    override val properties =
        language.strings.let { strings ->
            Properties(
                title = strings.page_home_title!!,
                description = strings.page_home_description!!,
            )
        }

    override val route = SitePaths.HOME_PATH

    @Composable
    override fun Content(ctx: PageContext) {
        context(language) {
            Div({ tw("grid auto-rows-min grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-5") }) {
                HomeHero(
                    Modifier.tw("col-span-1 row-span-2 md:col-span-2"),
                )

                HomeQuote()

                HomeLocation()

                HomeLatestPost(
                    post = BlogPosts[language]!!.first(),
                    modifier = Modifier.tw("md:col-span-2"),
                )

                HomeFeaturedProject(Modifier.tw("row-span-2 md:col-span-2"))

                HomeTechStack(Modifier.tw("md:col-span-2"))

                HomeSocials(Modifier.tw("md:col-span-2"))
            }
        }
    }
}
