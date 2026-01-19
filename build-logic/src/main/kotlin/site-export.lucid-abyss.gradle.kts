import com.varabyte.kobweb.gradle.application.KobwebApplicationPlugin
import com.varabyte.kobweb.gradle.application.extensions.app
import com.varabyte.kobweb.gradle.application.extensions.export
import com.varabyte.kobweb.gradle.core.extensions.kobwebBlock
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths

plugins.withType<KobwebApplicationPlugin> {
    kobwebBlock.app.export.apply {
        enableTraces()

        suppressNoRootWarning = true
        SiteLanguage.entries.forEach { language ->
            context(language) {
                addExtraRoute(SitePaths.home)
                addExtraRoute(SitePaths.about)
                addExtraRoute(SitePaths.blog)
                addExtraRoute(SitePaths.products)
            }
        }
    }
}
