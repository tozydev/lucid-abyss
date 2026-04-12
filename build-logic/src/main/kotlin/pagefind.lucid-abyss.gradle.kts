import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsExec

val jsMainCompilation =
    the<KotlinMultiplatformExtension>().js().compilations.getByName(KotlinCompilation.MAIN_COMPILATION_NAME)

NodeJsExec.register(jsMainCompilation, "pagefindIndex") {
    workingDir(project.projectDir)
    sourceMapStackTraces = false

    val pagefindBin = npmProject.nodeModulesDir.map { it.file("pagefind/lib/runner/bin.cjs").asFile.absolutePath }.get()
    nodeArgs = mutableListOf(pagefindBin)
}

// todo cleanup unused files produce by pagefind
