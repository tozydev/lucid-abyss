package vn.id.tozydev.lucidabyss.models

import com.varabyte.kobweb.navigation.BasePath
import vn.id.tozydev.lucidabyss.generated.filePathToPost
import vn.id.tozydev.lucidabyss.utils.formatDate
import kotlin.time.Instant

/** Represents the metadata of a blog post. */
data class Post(
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

/** Returns the full path of the cover image, or a default image if none is specified. */
val Post.coverImagePathOrDefault: String
    get() = BasePath.prependTo(coverImage ?: Constants.DEFAULT_COVER_IMAGE)

/** Returns the formatted date string for the post's publication date. */
val Post.publishedAtFormatted: String
    get() = publishedAt.formatDate()

/** Finds the next post based on its published date. */
val Post.nextPost: Post?
    get() = filePathToPost.values.sortedBy { it.publishedAt }.find { it.publishedAt > publishedAt }

/** Finds the previous post based on its published date. */
val Post.previousPost: Post?
    get() = filePathToPost.values.sortedBy { it.publishedAt }.findLast { it.publishedAt < publishedAt }
