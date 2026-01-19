package vn.id.tozydev.lucidabyss.core

object SitePaths {
    const val HOME_PATH = "/"
    const val ABOUT_PATH = "/me"
    const val PRODUCTS_EN_PATH = "/products"
    const val PRODUCTS_VI_PATH = "/san-pham"
    const val BLOG_PATH = "/blog"

    context(language: SiteLanguage)
    val home
        get() =
            when (language) {
                Default -> HOME_PATH
                else -> "/${language.code}"
            }

    context(language: SiteLanguage)
    val about
        get() =
            when (language) {
                Default -> ABOUT_PATH
                else -> "/${language.code}$ABOUT_PATH"
            }

    context(language: SiteLanguage)
    val blog
        get() =
            when (language) {
                Default -> BLOG_PATH
                else -> "/${language.code}$BLOG_PATH"
            }

    context(language: SiteLanguage)
    val products
        get() =
            when (language) {
                SiteLanguage.Vi -> PRODUCTS_VI_PATH
                SiteLanguage.En -> "/${language.code}$PRODUCTS_EN_PATH"
            }
}
