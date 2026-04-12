plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    compileOnly(libs.kobweb.common)
    compileOnly(libs.kobweb.server.plugin) {
        exclude(group = "io.netty")
    }
}

kotlin {
    jvmToolchain(21)
}
