plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(plugin(libs.plugins.kotlin.multiplatform))
    implementation(plugin(libs.plugins.compose.compiler))
    implementation(plugin(libs.plugins.kobweb.application))
    implementation(plugin(libs.plugins.kobwebx.markdown))

    implementation("vn.id.tozydev.lucidabyss:lucid-abyss-core")

    implementation(libs.kobwebx.frontmatter)
}

@Suppress("UnusedReceiverParameter")
fun DependencyHandlerScope.plugin(plugin: Provider<PluginDependency>): Provider<String> =
    plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }
