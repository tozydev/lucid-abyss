import com.varabyte.kobweb.gradle.application.KobwebApplicationPlugin
import com.varabyte.kobweb.gradle.core.extensions.kobwebBlock
import com.varabyte.kobweb.gradle.core.kmp.buildTargets
import com.varabyte.kobweb.gradle.core.kmp.kotlin
import com.varabyte.kobwebx.gradle.markdown.KobwebxMarkdownPlugin
import com.varabyte.kobwebx.gradle.markdown.MarkdownBlock
import com.varabyte.kobwebx.gradle.markdown.tasks.ProcessMarkdownTask
import org.jetbrains.kotlin.gradle.targets.js.ir.KotlinJsIrTarget
import vn.id.tozydev.lucidabyss.build.blog.ProcessBlogPostsTask
import vn.id.tozydev.lucidabyss.build.blog.processBlogMarkdowns

plugins.withType<KobwebApplicationPlugin> {
    apply<KobwebxMarkdownPlugin>()

    val processBlogPosts by tasks.registering(ProcessBlogPostsTask::class) {
        postsDir = rootProject.layout.projectDirectory.dir("blog")
        attachmentsDirName = ".attachments"
        attachmentPathPrefix = "/attachments"
        processedPostsDir = layout.buildDirectory.dir("generated/$name/src/jsMain/markdown")
        processedAttachmentsDir =
            layout.buildDirectory.dir("generated/$name/src/jsMain/resources")
    }

    tasks.withType<ProcessMarkdownTask> {
        dependsOn(processBlogPosts)
    }

    kobwebBlock.extensions.configure<MarkdownBlock> {
        process = { entries -> processBlogMarkdowns(entries) }

        addSource(processBlogPosts.flatMap { it.processedPostsDir }, ".pages.blog")
    }
    kobwebBlock

    buildTargets.withType<KotlinJsIrTarget> {
        kotlin.sourceSets.named("${name}Main") {
            resources.srcDirs(processBlogPosts.map { it.processedAttachmentsDir })
        }
    }
}
