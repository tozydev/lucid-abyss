import com.varabyte.kobweb.gradle.application.KobwebApplicationPlugin
import com.varabyte.kobweb.gradle.core.extensions.kobwebBlock
import com.varabyte.kobwebx.gradle.markdown.KobwebxMarkdownPlugin
import com.varabyte.kobwebx.gradle.markdown.MarkdownBlock
import com.varabyte.kobwebx.gradle.markdown.tasks.ProcessMarkdownTask
import vn.id.tozydev.lucidabyss.build.blog.ProcessBlogContentTask
import vn.id.tozydev.lucidabyss.build.blog.processBlogMarkdowns

plugins.withType<KobwebApplicationPlugin> {
    apply<KobwebxMarkdownPlugin>()

    val processBlogContent by tasks.registering(ProcessBlogContentTask::class) {
        blogContentDir = rootProject.layout.projectDirectory.dir("blog")
        processedBlogContentDir = layout.buildDirectory.dir("generated/$name/src/jsMain/resources/markdown")
    }

    tasks.withType<ProcessMarkdownTask> {
        dependsOn(processBlogContent)
    }

    kobwebBlock.extensions.configure<MarkdownBlock> {
        defaultLayout = ".components.layouts.PostLayout"
        process = { entries -> processBlogMarkdowns(entries) }

        addSource(processBlogContent.flatMap { it.processedBlogContentDir }, ".pages.blog")
    }
}
