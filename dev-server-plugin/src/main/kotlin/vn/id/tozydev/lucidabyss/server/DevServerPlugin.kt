package vn.id.tozydev.lucidabyss.server

import com.varabyte.kobweb.server.plugin.KobwebServerPlugin
import io.ktor.server.application.Application
import io.ktor.server.http.content.staticFiles
import io.ktor.server.routing.Routing
import io.ktor.server.routing.routing
import java.io.File

const val STATIC_ASSETS_PATH = "/_la"

class DevServerPlugin : KobwebServerPlugin {
    override fun configure(application: Application) {
        application.routing {
            configureStaticAssets()
        }
    }

    private fun Routing.configureStaticAssets() {
        staticFiles(
            remotePath = STATIC_ASSETS_PATH,
            dir = File("build/kotlin-webpack/js/developmentExecutable$STATIC_ASSETS_PATH"),
            index = null,
        ) {
            enableAutoHeadResponse()
        }
    }
}
