package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.PageContext
import strings.StringsEn
import strings.StringsVi
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths

class ProductPage(
    language: SiteLanguage,
) : Page(language) {
    override val route =
        when (language) {
            SiteLanguage.En -> SitePaths.PRODUCTS_EN_PATH
            else -> SitePaths.PRODUCTS_VI_PATH
        }
    override val properties =
        when (language) {
            SiteLanguage.En -> {
                Properties(
                    title = StringsEn.page_products_title,
                    description = StringsEn.page_products_description,
                )
            }

            else -> {
                Properties(
                    title = StringsVi.page_products_title,
                    description = StringsVi.page_products_description,
                )
            }
        }

    @Composable
    override fun Content(ctx: PageContext) {
        context(language) {
        }
    }
}
