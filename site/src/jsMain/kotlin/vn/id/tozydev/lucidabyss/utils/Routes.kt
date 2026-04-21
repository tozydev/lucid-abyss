package vn.id.tozydev.lucidabyss.utils

interface Routes {
    val home: String
    val about: String
    val blog: String
    val projects: String
    val rss: String

    fun blog(
        year: Int? = null,
        topic: String? = null,
    ): String

    fun tag(tag: String): String

    fun topic(topic: String): String
}

private object RouteImpl : Routes {
    override val home = "/"
    override val about = "/about"
    override val blog = "/blog"
    override val projects = "/projects"
    override val rss = "/rss.xml"

    override fun blog(
        year: Int?,
        topic: String?,
    ): String =
        buildString {
            append(blog)
            val queryParams =
                buildList {
                    if (year != null) {
                        add(BLOG_YEAR_PARAM to year.toString())
                    }
                    if (!topic.isNullOrBlank()) {
                        add(BLOG_TOPIC_PARAM to topic)
                    }
                }
            if (queryParams.isNotEmpty()) {
                append('?')
                append(queryParams.joinToString("&") { (key, value) -> "$key=$value" })
            }
        }

    override fun tag(tag: String) = "/tags/$tag"

    override fun topic(topic: String) = "/topics/$topic"
}

val SiteRoutes: Routes
    get() = RouteImpl

internal const val BLOG_YEAR_PARAM = "year"
internal const val BLOG_TOPIC_PARAM = "topic"
