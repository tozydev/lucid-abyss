package vn.id.tozydev.lucidabyss.server

import com.varabyte.kobweb.project.KobwebFolder
import com.varabyte.kobweb.project.conf.KobwebConf
import com.varabyte.kobweb.project.conf.KobwebConfFile
import com.varabyte.kobweb.server.api.ServerEnvironment
import com.varabyte.kobweb.server.plugin.KobwebServerPlugin
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.http.content.staticFiles
import io.ktor.server.response.respondPath
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.routing
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.exists
import kotlin.io.path.isRegularFile

const val STATIC_ASSETS_PATH = "/_la"

class DevServerPlugin : KobwebServerPlugin {
    override fun configure(application: Application) {
        val env = ServerEnvironment.get()
        if (env == ServerEnvironment.PROD) {
            return
        }

        val folder = requireNotNull(KobwebFolder.inWorkingDirectory())
        val conf = requireNotNull(KobwebConfFile(folder).content)

        application.routing {
            configureStaticAssets(conf)
        }
    }

    private fun Routing.configureStaticAssets(conf: KobwebConf) {
        val publicStaticAssetsPath = Path(conf.server.files.dev.contentRoot).resolve(STATIC_ASSETS_PATH.substring(1))
        staticFiles(
            remotePath = STATIC_ASSETS_PATH,
            dir = File("build/kotlin-webpack/js/developmentExecutable$STATIC_ASSETS_PATH"),
            index = null,
        ) {
            enableAutoHeadResponse()
            fallback { path, call ->
                val file = publicStaticAssetsPath.resolve(path).takeIf { it.exists() && it.isRegularFile() }
                if (file != null) {
                    call.respondPath(file)
                } else {
                    call.respondText("File not found", status = HttpStatusCode.NotFound)
                }
            }
        }
    }
}
