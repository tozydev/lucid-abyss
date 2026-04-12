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

val allPostTags: List<String> by lazy {
    Posts.allTags()
}

private val postsByTag: Map<String, List<Post>> by lazy {
    Posts
        .flatMap { post ->
            post.tags
                .filter { it.isNotBlank() }
                .map { tag -> tag to post }
        }.groupBy(
            keySelector = { it.first },
            valueTransform = { it.second },
        ).mapValues { (_, posts) ->
            posts.sortedByDescending { it.publishedAt }
        }
}

private val postsByTopic: Map<String, List<Post>> by lazy {
    Posts
        .filter { it.topic.isNotBlank() }
        .groupBy { it.topic }
        .mapValues { (_, posts) ->
            posts.sortedByDescending { it.publishedAt }
        }
}

private val tagsByTopic: Map<String, List<String>> by lazy {
    postsByTopic.mapValues { (_, posts) ->
        posts.allTags()
    }
}

fun postsForTag(tag: String): List<Post> =
    if (tag.isBlank()) {
        emptyList()
    } else {
        postsByTag[tag].orEmpty()
    }

fun postsForTopic(topic: String): List<Post> =
    if (topic.isBlank()) {
        emptyList()
    } else {
        postsByTopic[topic].orEmpty()
    }

fun tagsForTopic(topic: String): List<String> =
    if (topic.isBlank()) {
        emptyList()
    } else {
        tagsByTopic[topic].orEmpty()
    }

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
