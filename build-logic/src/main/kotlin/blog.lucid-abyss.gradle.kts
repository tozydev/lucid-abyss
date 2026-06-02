import com.varabyte.kobweb.gradle.application.KobwebApplicationPlugin
import com.varabyte.kobweb.gradle.core.extensions.kobwebBlock
import com.varabyte.kobweb.gradle.core.kmp.buildTargets
import com.varabyte.kobweb.gradle.core.kmp.kotlin
import com.varabyte.kobwebx.gradle.markdown.MarkdownBlock
import com.varabyte.kobwebx.gradle.markdown.tasks.ProcessMarkdownTask
import io.clroot.gradle.bun.task.BunTask
import org.jetbrains.kotlin.gradle.targets.js.ir.KotlinJsIrTarget
import vn.id.tozydev.lucidabyss.build.blog.ProcessBlogPostsTask
import vn.id.tozydev.lucidabyss.build.blog.processBlogMarkdowns
import vn.id.tozydev.lucidabyss.build.utils.ShikiCompilerService

plugins.withType<KobwebApplicationPlugin> {
    plugins.apply("com.varabyte.kobwebx.markdown")
    plugins.apply("io.clroot.gradle-bun")

    configure<io.clroot.gradle.bun.BunExtension> {
        version = "1.3.14"
        workingDir = rootProject.layout.projectDirectory.dir("utils")
    }

    val buildUtils by tasks.registering(BunTask::class) {
        dependsOn("bunInstall")
        workingDir = rootProject.layout.projectDirectory.dir("utils")

        val executableName = "utils"
        val outPath = "dist/$executableName"
        args.set(listOf("build", "--target=bun", "--compile", "--bytecode", "--outfile=$outPath", "src/index.ts"))

        inputs.dir(workingDir.map { it.dir("src") })
        inputs.file(workingDir.map { it.file("package.json") })
        inputs.file(workingDir.map { it.file("bun.lock") })
        inputs.file(workingDir.map { it.file("tsconfig.json") })

        outputs.file(workingDir.map { it.file(outPath.toPlatformExecutable()) })
    }

    val shikiCompilerService =
        gradle.sharedServices.registerIfAbsent(ShikiCompilerService.NAME, ShikiCompilerService::class.java) {
            parameters.executable = rootProject.layout.file(buildUtils.map { it.outputs.files.first() })
        }

    val processBlogPosts by tasks.registering(ProcessBlogPostsTask::class) {
        postsDir = rootProject.layout.projectDirectory.dir("blog")
        attachmentsDirName = ".attachments"
        attachmentPathPrefix = "/_la/attachments"
        processedPostsDir = layout.buildDirectory.dir("generated/$name/src/jsMain/markdown")
        processedAttachmentsDir = layout.buildDirectory.dir("generated/$name/src/jsMain/resources")
    }

    tasks.withType<ProcessMarkdownTask> {
        dependsOn(processBlogPosts, buildUtils)
        usesService(shikiCompilerService)
    }

    kobwebBlock.extensions.configure<MarkdownBlock> {
        process = { entries -> processBlogMarkdowns(entries) }

        addSource(processBlogPosts.flatMap { it.processedPostsDir }, ".pages.blog")
    }

    buildTargets.withType<KotlinJsIrTarget> {
        kotlin.sourceSets.named("${name}Main") {
            resources.srcDirs(processBlogPosts.map { it.processedAttachmentsDir })
        }
    }
}

val isWindows = System.getProperty("os.name").lowercase().contains("windows")

fun String.toPlatformExecutable() = if (isWindows) "$this.exe" else this
