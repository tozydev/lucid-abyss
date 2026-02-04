package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.PageContext
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths
import vn.id.tozydev.lucidabyss.utils.strings

class ProductPage(
    language: SiteLanguage,
) : Page(language) {
    override val route =
        when (language) {
            SiteLanguage.En -> SitePaths.PRODUCTS_EN_PATH
            else -> SitePaths.PRODUCTS_VI_PATH
        }
    override val properties =
        language.strings.let { strings ->
            Properties(
                title = strings.page_products_title!!,
                description = strings.page_products_description!!,
            )
        }

    @Composable
    override fun Content(ctx: PageContext) {
        context(language) {
        }
    }
}
