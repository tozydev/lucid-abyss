package vn.id.tozydev.lucidabyss.core

interface StaticRoute {
    val home: String
    val about: String
    val blog: String
    val projects: String
    val rss: String
}

object ViStaticRoute : StaticRoute {
    override val home = "/"
    override val about = "/about"
    override val blog = "/blog"
    override val projects = "/projects"
    override val rss = "/rss.xml"
}

object EnStaticRoute : StaticRoute {
    override val home = "/${SiteLanguage.En.code}/"
    override val about = "/${SiteLanguage.En.code}/about"
    override val blog = "/${SiteLanguage.En.code}/blog"
    override val projects = "/${SiteLanguage.En.code}/projects"
    override val rss = "/${SiteLanguage.En.code}/rss.xml"
}

context(language: SiteLanguage)
val SiteRoutes: StaticRoute
    get() =
        when (language) {
            SiteLanguage.Vi -> ViStaticRoute
            SiteLanguage.En -> EnStaticRoute
        }
