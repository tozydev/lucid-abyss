package vn.id.tozydev.lucidabyss.build.blog

import com.varabyte.kobwebx.gradle.markdown.MarkdownBlock
import com.varabyte.kobwebx.gradle.markdown.MarkdownEntry

fun MarkdownBlock.ProcessScope.processBlogMarkdowns(entries: List<MarkdownEntry>) {
    val posts = generateBlogPosts(entries)
    val code = generateBlogPostsCode(posts)

    generateKotlin("vn/id/tozydev/lucidabyss/generated/BlogPosts.kt", code)
}
