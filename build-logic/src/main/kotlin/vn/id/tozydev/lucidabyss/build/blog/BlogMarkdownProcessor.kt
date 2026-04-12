package vn.id.tozydev.lucidabyss.build.blog

import com.varabyte.kobwebx.gradle.markdown.MarkdownBlock
import com.varabyte.kobwebx.gradle.markdown.MarkdownEntry

fun MarkdownBlock.ProcessScope.processBlogMarkdowns(entries: List<MarkdownEntry>) {
    val posts = constructPosts(entries)
    val fileSpec = generatePostsFileSpec(posts)

    generateKotlin(fileSpec.relativePath, fileSpec.toString())
}
