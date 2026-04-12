package vn.id.tozydev.lucidabyss.build.blog

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.FileSystemOperations
import org.gradle.api.file.FileType
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.IgnoreEmptyDirectories
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.work.ChangeType
import org.gradle.work.FileChange
import org.gradle.work.Incremental
import org.gradle.work.InputChanges
import java.io.File
import javax.inject.Inject
import kotlin.io.path.createParentDirectories
import kotlin.io.path.deleteIfExists
import kotlin.io.path.writeText

@CacheableTask
abstract class ProcessBlogPostsTask : DefaultTask() {
    @get:Incremental
    @get:PathSensitive(PathSensitivity.RELATIVE)
    @get:IgnoreEmptyDirectories
    @get:InputDirectory
    abstract val postsDir: DirectoryProperty

    @get:Input
    abstract val attachmentsDirName: Property<String>

    @get:Input
    abstract val attachmentPathPrefix: Property<String>

    @get:OutputDirectory
    abstract val processedPostsDir: DirectoryProperty

    @get:OutputDirectory
    abstract val processedAttachmentsDir: DirectoryProperty

    @get:Inject
    abstract val fs: FileSystemOperations

    private val attachmentsDirNameValue by lazy { attachmentsDirName.get() }

    private val attachmentPathPrefixValue by lazy { attachmentPathPrefix.get() }

    @TaskAction
    fun run(inputChanges: InputChanges) {
        inputChanges
            .getFileChanges(postsDir)
            .filter { it.fileType == FileType.FILE }
            .forEach { change ->
                val file = change.file
                when {
                    file.parentFile?.name == attachmentsDirNameValue -> {
                        processSingleAttachment(change)
                    }

                    file.isFile && file.name.endsWith(".md") -> {
                        processPosts(change)
                    }
                }
            }
    }

    private fun processPosts(change: FileChange) {
        val postSlug = change.file.postSlug
        val pascalCaseSlug = postSlug.toPascalCase
        val targetFile =
            processedPostsDir
                .file("$pascalCaseSlug.md")
                .get()
                .asFile
                .toPath()

        if (change.changeType == ChangeType.REMOVED) {
            targetFile.deleteIfExists()
        } else {
            targetFile.createParentDirectories()
            val processedContent = change.file.processFrontmatter(postSlug, pascalCaseSlug)
            targetFile.writeText(processedContent)
        }
    }

    private fun processSingleAttachment(change: FileChange) {
        val file = change.file
        val postSlug = file.postSlug

        val attachmentsRoot = "${File.separatorChar}$attachmentsDirNameValue${File.separatorChar}"
        val relativePathInAttachments = path.substringAfter(attachmentsRoot)

        val targetFile =
            processedAttachmentsDir
                .dir("public${File.separatorChar}${attachmentPathPrefixValue}${File.separatorChar}$postSlug")
                .get()
                .file(relativePathInAttachments)
                .asFile

        when (change.changeType) {
            ChangeType.REMOVED -> {
                targetFile.delete()
            }

            else -> {
                fs.copy {
                    from(file)
                    into(targetFile.parentFile)
                }
            }
        }
    }

    private val File.postSlug
        get() = if (parentFile.name == attachmentsDirNameValue) parentFile.parentFile.name else parentFile.name

    private val String.toPascalCase
        get() = split("-").joinToString("") { part -> part.replaceFirstChar { it.titlecase() } }

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
            setFrontmatterProperty("routeOverride", routeOverride)
            setFrontmatterProperty("funName", funName)
        }

        val processedContent =
            content
                .joinToString("\n")
                .replace("$attachmentsDirNameValue/", "$attachmentPathPrefixValue/$routeOverride/")

        return buildString {
            appendLine("---")
            frontmatter.forEach { appendLine(it) }
            appendLine("---")
            append(processedContent)
        }
    }
}
