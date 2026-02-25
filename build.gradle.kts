plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kobweb.application) apply false
    alias(libs.plugins.kobwebx.markdown) apply false
    id("formatting.lucid-abyss")
}
