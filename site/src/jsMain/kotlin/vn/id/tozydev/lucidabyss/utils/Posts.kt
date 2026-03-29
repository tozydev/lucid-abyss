package vn.id.tozydev.lucidabyss.utils

import com.varabyte.kobweb.navigation.BasePath
import vn.id.tozydev.lucidabyss.generated.Post
import vn.id.tozydev.lucidabyss.generated.Posts

val Post.coverImagePathOrDefault: String
    get() = BasePath.prependTo(coverImage ?: "/images/default-cover.webp")

fun List<Post>.allTags(): List<String> =
    flatMap { it.tags }
        .distinct()
        .filter { it.isNotBlank() }

private val postSlugIndices: Map<String, Int> by lazy {
    Posts.withIndex().associate { (index, post) -> post.slug to index }
}

internal fun findPost(slug: String) = postSlugIndices[slug]?.let { Posts[it] }

val Post.nextPost: Post?
    get() =
        postSlugIndices[slug]?.let { index ->
            Posts.getOrNull(index - 1)
        }

val Post.previousPost: Post?
    get() =
        postSlugIndices[slug]?.let { index ->
            Posts.getOrNull(index + 1)
        }
