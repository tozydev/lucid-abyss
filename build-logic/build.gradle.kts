plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

dependencies {
    implementation("com.squareup:kotlinpoet:2.0.0")
    implementation("org.yaml:snakeyaml:2.2")
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
