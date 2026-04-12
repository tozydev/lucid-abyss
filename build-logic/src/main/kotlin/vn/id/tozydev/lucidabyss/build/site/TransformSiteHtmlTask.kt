package vn.id.tozydev.lucidabyss.build.site

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.util.function.Consumer

abstract class TransformSiteHtmlTask : DefaultTask() {
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val sourceDir: DirectoryProperty

    @get:Input
    abstract val modifier: Property<Consumer<Document>>

    @TaskAction
    fun process() {
        val modifier = modifier.get()
        sourceDir.asFileTree.filter { it.isHtml }.forEach { file ->
            val document = Jsoup.parse(file, Charsets.UTF_8.name())
            modifier.accept(document)
            file.writeText(document.html(), Charsets.UTF_8)
        }
    }

    private val File.isHtml get() = extension == "html"
}
