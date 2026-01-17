import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.targets.js.npm.NpmDependency

fun KotlinDependencyHandler.npm(dependency: Provider<MinimalExternalModuleDependency>): Provider<NpmDependency> =
    dependency.map {
        NpmDependency(
            objectFactory = project.objects,
            name = it.name,
            version = it.version!!,
            scope = NpmDependency.Scope.valueOf(it.group!!.uppercase()),
        )
    }
