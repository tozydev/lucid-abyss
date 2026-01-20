package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.PageContext
import strings.StringsEn
import strings.StringsVi
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths

class AboutPage(
    language: SiteLanguage,
) : Page(language) {
    override val route = SitePaths.ABOUT_PATH
    override val properties =
        when (language) {
            SiteLanguage.En -> {
                Properties(
                    title = StringsEn.page_about_title,
                    description = StringsEn.page_about_description,
                )
            }

            else -> {
                Properties(
                    title = StringsVi.page_about_title,
                    description = StringsVi.page_about_description,
                )
            }
        }

    @Composable
    override fun content(ctx: PageContext) {
        context(language) {
        }
    }
}
