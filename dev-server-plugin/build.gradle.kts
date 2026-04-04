plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    compileOnly(libs.kobweb.server.plugin)
}

kotlin {
    jvmToolchain(21)
}
