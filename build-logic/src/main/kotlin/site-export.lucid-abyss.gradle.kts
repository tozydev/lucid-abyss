import com.varabyte.kobweb.gradle.application.KobwebApplicationPlugin
import com.varabyte.kobweb.gradle.application.extensions.app
import com.varabyte.kobweb.gradle.application.extensions.export
import com.varabyte.kobweb.gradle.core.extensions.kobwebBlock

plugins.withType<KobwebApplicationPlugin> {
    kobwebBlock.app.export.apply {
        enableTraces()
        configureRoutes()
        suppressNoRootWarning = true
    }
}
