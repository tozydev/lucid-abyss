plugins {
    `kotlin-dsl`
}

kotlin {
    compilerOptions {
        optIn.addAll("kotlin.time.ExperimentalTime")
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

dependencies {
    implementation(libs.kotlinpoet)
    implementation(libs.snakeyaml)
    implementation(libs.kobwebx.frontmatter)
    implementation(libs.jsoup)
    implementation(libs.gson)

    implementation(plugin(libs.plugins.kotlin.multiplatform))
    implementation(plugin(libs.plugins.compose.compiler))
    implementation(plugin(libs.plugins.kobweb.application))
    implementation(plugin(libs.plugins.kobwebx.markdown))
    implementation(plugin(libs.plugins.spotless))
    implementation(plugin(libs.plugins.bun))
}

@Suppress("UnusedReceiverParameter")
fun DependencyHandlerScope.plugin(plugin: Provider<PluginDependency>): Provider<String> =
    plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }
