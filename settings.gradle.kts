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

includeBuild("core")
include("site")
