package vn.id.tozydev.lucidabyss.core

import kotlin.jvm.JvmInline
import kotlin.time.Instant

@JvmInline
value class PostId(
    val value: String,
)

data class BlogPost(
    val id: PostId,
    val metadata: Map<SiteLanguage, PostMetadata>,
)

data class PostMetadata(
    val language: SiteLanguage,
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
