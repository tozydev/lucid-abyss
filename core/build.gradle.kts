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
        freeCompilerArgs.addAll("-Xcontext-parameters", "-Xcontext-sensitive-resolution")
        optIn.addAll("kotlin.time.ExperimentalTime")
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.coroutines.core)
        }
    }
}
