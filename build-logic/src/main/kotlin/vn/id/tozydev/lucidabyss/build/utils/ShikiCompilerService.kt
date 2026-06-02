package vn.id.tozydev.lucidabyss.build.utils

import com.google.gson.Gson
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.logging.Logging
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import java.io.BufferedReader
import java.io.BufferedWriter
import java.util.concurrent.atomic.AtomicInteger

abstract class ShikiCompilerService :
    BuildService<ShikiCompilerService.Params>,
    AutoCloseable {
    interface Params : BuildServiceParameters {
        val executable: RegularFileProperty
    }

    private data class ShikiRequest(
        val id: Int,
        val command: String,
        val code: String,
        val lang: String,
    )

    private data class ShikiResponse(
        val id: Int?,
        val result: String?,
        val error: String?,
    )

    companion object {
        private val logger = Logging.getLogger(ShikiCompilerService::class.java)
        const val NAME = "shikiCompiler"
    }

    private val process: Process
    private val writer: BufferedWriter
    private val reader: BufferedReader

    private val gson = Gson()

    private val requestId = AtomicInteger(0)

    init {
        val exec = parameters.executable.get().asFile
        require(exec.exists()) { "Shiki compiler executable not found at: ${exec.absolutePath}" }

        val pb = ProcessBuilder(exec.absolutePath)
        process = pb.start()
        writer = process.outputStream.bufferedWriter(Charsets.UTF_8)
        reader = process.inputStream.bufferedReader(Charsets.UTF_8)

        // Consume error stream to avoid blocking the process
        Thread.ofVirtual().start {
            runCatching {
                process.errorReader(Charsets.UTF_8).use { errReader ->
                    var line = errReader.readLine()
                    while (line != null) {
                        logger.error(line)
                        line = errReader.readLine()
                    }
                }
            }
        }
    }

    @Synchronized
    fun highlight(
        code: String,
        lang: String,
    ): String {
        check(process.isAlive) { "Shiki compiler process is not running" }

        val id = requestId.incrementAndGet()
        val request = ShikiRequest(id = id, command = "shiki", code = code, lang = lang)
        val requestJson = gson.toJson(request)

        writer.write(requestJson)
        writer.newLine()
        writer.flush()

        val responseJson = checkNotNull(reader.readLine()) { "IPC server closed stream unexpectedly" }
        val response =
            checkNotNull(
                gson.fromJson(
                    responseJson,
                    ShikiResponse::class.java,
                ),
            ) { "IPC server returned empty response" }

        if (response.error != null) {
            throw RuntimeException("Shiki IPC Server error: ${response.error}")
        }
        check(response.id == id) { "Mismatch IPC request-response IDs. Expected $id, got ${response.id}" }
        return checkNotNull(response.result) { "IPC response has no result or error" }
    }

    override fun close() {
        if (process.isAlive) {
            reader.close()
            writer.close()
            process.destroyForcibly()
        }
    }
}
