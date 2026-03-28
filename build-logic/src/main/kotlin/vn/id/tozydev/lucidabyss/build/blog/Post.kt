package vn.id.tozydev.lucidabyss.build.blog

import com.varabyte.kobwebx.frontmatter.FrontMatterElement
import com.varabyte.kobwebx.gradle.markdown.MarkdownEntry
import kotlin.time.Instant

/**
 * Represents a blog post metadata.
 */
data class Post(
    val slug: String,
    val route: String,
    val title: String,
    val description: String,
    val author: String,
    val publishedAt: Instant,
    val modifiedAt: Instant?,
    val topic: String,
    val tags: Set<String>,
    val coverImage: String?,
)

internal fun constructPosts(entries: List<MarkdownEntry>): List<Post> =
    entries
        .filter { it.route.contains("/blog/") }
        .map { it.metadata }
        .sortedByDescending { it.publishedAt }

private val MarkdownEntry.metadata
    get() =
        Post(
            slug = frontMatter.getString("routeOverride"),
            route = route,
            title = frontMatter.getString("title"),
            description = frontMatter.getString("description"),
            author = frontMatter.getString("author"),
            publishedAt = frontMatter.getInstant("publishedAt"),
            modifiedAt = frontMatter.getInstantOrNull("modifiedAt"),
            topic = frontMatter.getString("topic"),
            tags = frontMatter.getSet("tags"),
            coverImage = frontMatter.getStringOrNull("coverImage"),
        )

private fun FrontMatterElement.getString(path: String) =
    requireNotNull(this[path]?.singleOrNull()) { "Missing required string value for path: $path" }

private fun FrontMatterElement.getStringOrNull(path: String) = this[path]?.singleOrNull()

private fun FrontMatterElement.getInstant(path: String) =
    requireNotNull(this[path]?.singleOrNull()?.let(Instant::parse)) { "Missing required Instant value for path: $path" }

private fun FrontMatterElement.getInstantOrNull(path: String) = this[path]?.singleOrNull()?.let(Instant::parse)

private fun FrontMatterElement.getSet(path: String) = this[path]?.toSet() ?: emptySet()
