@file:OptIn(ExperimentalTime::class)

package vn.id.tozydev.lucidabyss.build.blog

import vn.id.tozydev.lucidabyss.core.BlogPost
import vn.id.tozydev.lucidabyss.core.PostId
import vn.id.tozydev.lucidabyss.core.PostMetadata
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import kotlin.time.ExperimentalTime

internal fun generateBlogPostsCode(posts: Map<PostId, BlogPost>): String =
    buildString {
        appendLine("@file:OptIn(ExperimentalTime::class)")
        appendLine()
        appendLine("package vn.id.tozydev.lucidabyss.generated")
        appendLine()
        appendLine("import vn.id.tozydev.lucidabyss.core.BlogPost")
        appendLine("import vn.id.tozydev.lucidabyss.core.PostId")
        appendLine("import vn.id.tozydev.lucidabyss.core.PostMetadata")
        appendLine("import vn.id.tozydev.lucidabyss.core.SiteLanguage")
        appendLine("import kotlin.time.ExperimentalTime")
        appendLine("import kotlin.time.Instant")
        appendLine()
        appendLine("public val BlogPosts: Map<PostId, BlogPost> =")
        appendLine(4) {
            appendLine("mapOf(")
            posts.forEach { (id, post) ->
                appendBlogEntry(id, post)
            }
            appendLine(")")
        }
    }

private fun StringBuilder.appendBlogEntry(
    id: PostId,
    post: BlogPost,
) {
    appendLine(4) {
        appendLine("PostId(\"${id.value}\") to")
        appendLine(4) {
            appendLine("BlogPost(")
            appendLine(4) {
                appendLine("id = PostId(\"${id.value}\"),")
                appendLine("metadata =")
                appendLine(4) {
                    appendLine("mapOf(")
                    post.metadata.forEach { (lang, meta) ->
                        appendPostMetadata(lang, meta)
                    }
                    appendLine("),")
                }
            }
            appendLine("),")
        }
    }
}

private fun StringBuilder.appendPostMetadata(
    lang: SiteLanguage,
    meta: PostMetadata,
) {
    appendLine(4) {
        appendLine("SiteLanguage.${lang.name} to")
        appendLine(4) {
            appendLine("PostMetadata(")
            appendLine(4) {
                appendLine("language = SiteLanguage.${lang.name},")
                appendLine("route = \"${meta.route}\",")
                appendLine("title = \"\"\"${meta.title}\"\"\",")
                appendLine("description = \"\"\"${meta.description}\"\"\",")
                appendLine("author = \"${meta.author}\",")
                appendLine("publishedAt = Instant.parse(\"${meta.publishedAt}\"),")
                appendLine("modifiedAt = ${meta.modifiedAt?.let { "Instant.parse(\"$it\")" } ?: "null"},")
                appendLine("topic = \"${meta.topic}\",")
                appendLine("tags =")
                appendLine(4) {
                    appendLine("setOf(")
                    meta.tags.forEach { tag ->
                        appendLine(4) {
                            appendLine("\"$tag\",")
                        }
                    }
                    appendLine("),")
                }
                appendLine("coverImage = ${meta.coverImage?.let { "\"$it\"" } ?: "null"},")
            }
            appendLine("),")
        }
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
