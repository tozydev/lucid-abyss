package vn.id.tozydev.lucidabyss.utils

interface Routes {
    val home: String
    val about: String
    val blog: String
    val projects: String
    val rss: String

    fun blog(year: Int? = null): String

    fun tag(tag: String): String

    fun topic(topic: String): String
}

private object RouteImpl : Routes {
    override val home = "/"
    override val about = "/about"
    override val blog = "/blog"
    override val projects = "/projects"
    override val rss = "/rss.xml"

    override fun blog(year: Int?): String =
        buildString {
            append(blog)
            if (year != null) {
                append("?$BLOG_YEAR_PARAM=$year")
            }
        }

    override fun tag(tag: String) = "/tags/$tag"

    override fun topic(topic: String) = "/topics/$topic"
}

val SiteRoutes: Routes
    get() = RouteImpl

internal const val BLOG_YEAR_PARAM = "year"
