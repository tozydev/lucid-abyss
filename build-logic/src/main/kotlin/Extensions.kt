import com.varabyte.kobweb.gradle.application.extensions.AppBlock
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.targets.js.npm.NpmDependency
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths

fun KotlinDependencyHandler.npm(dependency: Provider<MinimalExternalModuleDependency>): Provider<NpmDependency> =
    dependency.map {
        NpmDependency(
            objectFactory = project.objects,
            name = it.name,
            version = it.version!!,
            scope = NpmDependency.Scope.valueOf(it.group.uppercase()),
        )
    }

internal fun AppBlock.ExportBlock.configureRoutes() {
    SiteLanguage.entries.forEach { language ->
        context(language) {
            addExtraRoute(SitePaths.home)
            addExtraRoute(SitePaths.about)
            addExtraRoute(SitePaths.blog)
            addExtraRoute(SitePaths.projects)
        }
    }
}
