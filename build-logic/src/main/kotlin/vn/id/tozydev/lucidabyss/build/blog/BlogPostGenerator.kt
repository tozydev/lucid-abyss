@file:OptIn(ExperimentalTime::class)

package vn.id.tozydev.lucidabyss.build.blog

import com.varabyte.kobwebx.frontmatter.FrontMatterElement
import com.varabyte.kobwebx.gradle.markdown.MarkdownEntry
import vn.id.tozydev.lucidabyss.core.BlogPost
import vn.id.tozydev.lucidabyss.core.PostId
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

internal fun generateBlogPosts(entries: List<MarkdownEntry>): Map<SiteLanguage, List<BlogPost>> =
    entries
        .filter { it.route.contains("/blog/") }
        .groupBy { it.language }
        .mapValues { (_, entries) ->
            entries.map { it.metadata }.sortedByDescending { it.publishedAt }
        }

private val MarkdownEntry.metadata: BlogPost
    get() =
        BlogPost(
            id = postId,
            language = language,
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

private val MarkdownEntry.postId: PostId get() = frontMatter.getString("id").let(::PostId)

/**
 * Retrieves the language associated with the current Markdown entry.
 *
 * Example: `/blog-content/vi/2026-01-01.bai-viet.md` -> `Language.Vi`
 */
private val MarkdownEntry.language: SiteLanguage
    get() = SiteLanguage.fromCode(filePath.substringBefore("/"))

private fun FrontMatterElement.getString(path: String) =
    requireNotNull(this[path]?.singleOrNull()) { "Missing required string value for path: $path" }

private fun FrontMatterElement.getStringOrNull(path: String) = this[path]?.singleOrNull()

private fun FrontMatterElement.getInstant(path: String) =
    requireNotNull(this[path]?.singleOrNull()?.let(Instant::parse)) { "Missing required Instant value for path: $path" }

private fun FrontMatterElement.getInstantOrNull(path: String) = this[path]?.singleOrNull()?.let(Instant::parse)

private fun FrontMatterElement.getSet(path: String) = this[path]?.toSet() ?: emptySet()
