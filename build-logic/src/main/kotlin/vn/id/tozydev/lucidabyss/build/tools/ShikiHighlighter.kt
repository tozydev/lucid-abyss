package vn.id.tozydev.lucidabyss.build.tools

import org.gradle.process.ProcessExecutionException
import java.io.File
import java.util.concurrent.TimeUnit

@Suppress("unused")
fun highlightCodeWithShiki(
    toolsExecutable: File,
    code: String,
    lang: String,
): String {
    val processBuilder =
        ProcessBuilder(
            toolsExecutable.absolutePath,
            "shiki",
            "-",
            "-l",
            lang,
        )

    try {
        val process = processBuilder.start()
        process.outputWriter(Charsets.UTF_8).use { it.write(code) }
        val output = process.inputReader(Charsets.UTF_8).use { it.readText() }
        val error = process.errorReader(Charsets.UTF_8).use { it.readText() }
        val exitCode = process.waitFor(5, TimeUnit.SECONDS).let { if (it) process.exitValue() else -1 }
        if (exitCode != 0) {
            throw ProcessExecutionException("Shiki CLI failed with exit code $exitCode. Error: $error")
        }
        return output
    } catch (e: Exception) {
        throw ProcessExecutionException(
            "Failed to run Shiki CLI (executable: ${toolsExecutable.absolutePath}, lang: $lang): ${e.message}",
            e,
        )
    }
}
