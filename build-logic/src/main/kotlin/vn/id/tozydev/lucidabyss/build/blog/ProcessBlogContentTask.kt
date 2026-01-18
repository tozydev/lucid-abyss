package vn.id.tozydev.lucidabyss.build.blog

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.IgnoreEmptyDirectories
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.work.ChangeType
import org.gradle.work.Incremental
import org.gradle.work.InputChanges
import vn.id.tozydev.lucidabyss.core.PostId
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import java.io.File
import kotlin.io.path.createParentDirectories
import kotlin.io.path.deleteIfExists
import kotlin.io.path.writeText

@CacheableTask
abstract class ProcessBlogContentTask : DefaultTask() {
    @get:Incremental
    @get:PathSensitive(PathSensitivity.RELATIVE)
    @get:IgnoreEmptyDirectories
    @get:InputDirectory
    abstract val blogContentDir: DirectoryProperty

    @get:OutputDirectory
    abstract val processedBlogContentDir: DirectoryProperty

    @TaskAction
    fun run(inputChanges: InputChanges) {
        inputChanges.getFileChanges(blogContentDir).forEach { change ->
            if (change.file.isDirectory) {
                return@forEach
            }

            val postSlug = change.file.postSlug
            val pascalCaseSlug =
                postSlug.split("-").joinToString("") { part -> part.replaceFirstChar { it.titlecase() } }
            val targetFile =
                processedBlogContentDir
                    .file(
                        change.file.parentFile
                            .relativeTo(blogContentDir.get().asFile)
                            .path + "/$pascalCaseSlug.md",
                    ).get()
                    .asFile
                    .toPath()
            if (change.changeType == ChangeType.REMOVED) {
                targetFile.deleteIfExists()
                return@forEach
            }

            val processedContent = change.file.processFrontmatter(postSlug, pascalCaseSlug)

            targetFile.createParentDirectories()
            targetFile.writeText(processedContent)
        }
    }

    private val File.postId get() = PostId(nameWithoutExtension.substringBefore("."))

    private val File.postSlug get() = nameWithoutExtension.substringAfter(".")

    private val File.language get() = SiteLanguage.fromCode(parentFile.name)

    private fun File.processFrontmatter(
        routeOverride: String,
        funName: String,
    ): String {
        val lines = readLines()
        val hasFrontmatter = lines.firstOrNull() == "---"
        val frontmatterEndIndex = if (hasFrontmatter) lines.drop(1).indexOf("---") + 1 else -1

        fun MutableList<String>.setFrontmatterProperty(
            key: String,
            value: String,
        ) {
            val index = indexOfFirst { it.startsWith("$key:") }
            val entry = "$key: $value"
            if (index != -1) {
                this[index] = entry
            } else {
                add(0, entry)
            }
        }

        val (frontmatter, content) =
            if (hasFrontmatter && frontmatterEndIndex > 0) {
                val extracted = lines.subList(1, frontmatterEndIndex).toMutableList()
                val remaining = lines.subList(frontmatterEndIndex + 1, lines.size)
                extracted to remaining
            } else {
                mutableListOf<String>() to lines
            }

        frontmatter.apply {
            setFrontmatterProperty("language", language.code)
            setFrontmatterProperty("id", postId.value)
            setFrontmatterProperty("routeOverride", routeOverride)
            setFrontmatterProperty("funName", funName)
        }

        return buildString {
            appendLine("---")
            frontmatter.forEach { appendLine(it) }
            appendLine("---")
            append(content.joinToString("\n"))
        }
    }
}
