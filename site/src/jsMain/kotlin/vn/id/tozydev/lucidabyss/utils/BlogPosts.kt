package vn.id.tozydev.lucidabyss.utils

import com.varabyte.kobweb.navigation.BasePath
import com.varabyte.kobwebx.markdown.MarkdownContext
import vn.id.tozydev.lucidabyss.core.BlogPost
import vn.id.tozydev.lucidabyss.core.PostId
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.generated.BlogPosts
import vn.id.tozydev.lucidabyss.models.Constants

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
    get() = BasePath.prependTo(coverImage ?: Constants.DEFAULT_COVER_IMAGE)

val BlogPost.nextPost: BlogPost?
    get() =
        BlogPosts[language]?.let { posts ->
            val index = posts.indexOfFirst { it.id == id }
            if (index > 0) posts[index - 1] else null
        }

val BlogPost.previousPost: BlogPost?
    get() =
        BlogPosts[language]?.let { posts ->
            val index = posts.indexOfFirst { it.id == id }
            if (index != -1 && index < posts.size - 1) posts[index + 1] else null
        }
