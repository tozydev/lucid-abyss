@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }

    versionCatalogs {
        create("npm") {
            from(files("gradle/npm.versions.toml"))
        }
    }
}

rootProject.name = "lucid-abyss"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include("site", "dev-server-plugin")
