package vn.id.tozydev.lucidabyss.models

import com.varabyte.kobweb.navigation.BasePath
import vn.id.tozydev.lucidabyss.models.Constants.EMAIL_HASH
import vn.id.tozydev.lucidabyss.utils.getGravatarUrl
import kotlin.time.Instant

/** Represents the metadata of a blog post. */
data class Post(
    val route: String,
    val title: String,
    val description: String,
    val author: String,
    val publishedAt: Instant,
    val modifiedAt: Instant?,
    val tags: Set<String>,
    val coverImage: String?,
)

/** Returns the full path of the cover image, or a default image if none is specified. */
val Post.coverImagePathOrDefault: String
    get() = BasePath.prependTo(coverImage ?: Constants.DEFAULT_COVER_IMAGE)

@Suppress("UnusedReceiverParameter")
val Post.authorAvatarUrl: String
    get() = getGravatarUrl(EMAIL_HASH, size = 48)

@Suppress("UnusedReceiverParameter")
val Post.authorWebsite: String
    get() = "/about"
