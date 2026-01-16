plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvmToolchain(21)

    jvm()
    js {
        browser()
    }

    compilerOptions {
        optIn.addAll("kotlin.time.ExperimentalTime")
    }
}
