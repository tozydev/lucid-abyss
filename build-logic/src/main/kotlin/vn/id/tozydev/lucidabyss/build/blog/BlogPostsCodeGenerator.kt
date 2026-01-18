@file:OptIn(ExperimentalTime::class)

package vn.id.tozydev.lucidabyss.build.blog

import vn.id.tozydev.lucidabyss.core.BlogPost
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import kotlin.time.ExperimentalTime

internal fun generateBlogPostsCode(posts: Map<SiteLanguage, List<BlogPost>>): String =
    buildString {
        appendLine("@file:OptIn(ExperimentalTime::class)")
        appendLine()
        appendLine("package vn.id.tozydev.lucidabyss.generated")
        appendLine()
        appendLine("import vn.id.tozydev.lucidabyss.core.BlogPost")
        appendLine("import vn.id.tozydev.lucidabyss.core.PostId")
        appendLine("import vn.id.tozydev.lucidabyss.core.SiteLanguage")
        appendLine("import kotlin.time.ExperimentalTime")
        appendLine("import kotlin.time.Instant")
        appendLine()
        appendLine("public val BlogPosts: Map<SiteLanguage, List<BlogPost>> =")
        appendLine(4) {
            appendLine("mapOf(")
            posts.forEach { (language, blogPosts) ->
                appendLanguageEntry(language, blogPosts)
            }
            appendLine(")")
        }
    }

private fun StringBuilder.appendLanguageEntry(
    language: SiteLanguage,
    posts: List<BlogPost>,
) {
    appendLine(4) {
        appendLine("SiteLanguage.${language.name} to")
        appendLine(4) {
            appendLine("listOf(")
            posts.forEach { post ->
                appendBlogPost(post)
            }
            appendLine("),")
        }
    }
}

private fun StringBuilder.appendBlogPost(post: BlogPost) {
    appendLine(4) {
        appendLine("BlogPost(")
        appendLine(4) {
            appendLine("id = PostId(\"${post.id.value}\"),")
            appendLine("language = SiteLanguage.${post.language.name},")
            appendLine("route = \"${post.route}\",")
            appendLine("title = \"\"\"${post.title}\"\"\",")
            appendLine("description = \"\"\"${post.description}\"\"\",")
            appendLine("author = \"${post.author}\",")
            appendLine("publishedAt = Instant.parse(\"${post.publishedAt}\"),")
            appendLine("modifiedAt = ${post.modifiedAt?.let { "Instant.parse(\"$it\")" } ?: "null"},")
            appendLine("topic = \"${post.topic}\",")
            appendLine("tags =")
            appendLine(4) {
                appendLine("setOf(")
                post.tags.forEach { tag ->
                    appendLine(4) {
                        appendLine("\"$tag\",")
                    }
                }
                appendLine("),")
            }
            appendLine("coverImage = ${post.coverImage?.let { "\"$it\"" } ?: "null"},")
        }
        appendLine("),")
    }
}

private inline fun StringBuilder.appendLine(
    indent: Int,
    block: StringBuilder.() -> Unit,
) {
    val content = StringBuilder().apply(block).toString()
    if (content.isNotEmpty()) {
        content.lines().forEach { line ->
            if (line.isNotEmpty()) {
                appendLine(" ".repeat(indent) + line)
            }
        }
    }
}
