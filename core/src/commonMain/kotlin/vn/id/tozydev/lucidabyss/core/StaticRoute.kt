package vn.id.tozydev.lucidabyss.core

interface StaticRoute {
    val home: String
    val about: String
    val blog: String
    val projects: String
    val rss: String
    val tagPattern: String
    val topicPattern: String

    fun tag(tag: String): String

    fun topic(topic: String): String
}

object ViStaticRoute : StaticRoute {
    override val home = "/"
    override val about = "/about"
    override val blog = "/blog"
    override val projects = "/projects"
    override val rss = "/rss.xml"
    override val tagPattern = "/tags/{tag}"
    override val topicPattern = "/topics/{topic}"

    override fun tag(tag: String) = "/tags/$tag"

    override fun topic(topic: String) = "/topics/$topic"
}

val SiteRoutes: StaticRoute
    get() = ViStaticRoute
