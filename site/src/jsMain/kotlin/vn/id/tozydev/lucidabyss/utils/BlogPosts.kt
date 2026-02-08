package vn.id.tozydev.lucidabyss.utils

import com.varabyte.kobweb.navigation.BasePath
import com.varabyte.kobwebx.markdown.MarkdownContext
import vn.id.tozydev.lucidabyss.core.BlogPost
import vn.id.tozydev.lucidabyss.core.PostId
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.generated.BlogPosts

val MarkdownContext.postId: PostId
    get() =
        checkNotNull(frontMatter.getValue("id").singleOrNull()?.let(::PostId)) {
            "Missing post ID for $path"
        }

val MarkdownContext.language: SiteLanguage
    get() =
        checkNotNull(frontMatter.getValue("language").singleOrNull()?.let(SiteLanguage::fromCode)) {
            "Missing language for $path"
        }

val BlogPost.coverImagePathOrDefault: String
    get() = BasePath.prependTo(coverImage ?: "/images/default-cover.webp")

private val postIndices: Map<SiteLanguage, Map<PostId, Int>> by lazy {
    BlogPosts.mapValues { (_, posts) ->
        posts.withIndex().associate { it.value.id to it.index }
    }
}

fun getBlogPost(
    language: SiteLanguage,
    id: PostId,
): BlogPost? =
    postIndices[language]?.get(id)?.let { index ->
        BlogPosts[language]?.getOrNull(index)
    }

val BlogPost.nextPost: BlogPost?
    get() =
        postIndices[language]?.get(id)?.let { index ->
            BlogPosts[language]?.getOrNull(index - 1)
        }

val BlogPost.previousPost: BlogPost?
    get() =
        postIndices[language]?.get(id)?.let { index ->
            BlogPosts[language]?.getOrNull(index + 1)
        }
