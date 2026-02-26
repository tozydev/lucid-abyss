plugins {
    id("com.diffplug.spotless")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

spotless {
    kotlinGradle {
        target(
            "*.gradle.kts",
            "build-logic/*.gradle.kts",
            "build-logic/src/**/*.kts",
            "core/*.gradle.kts",
            "site/*.gradle.kts",
        )
        ktlint()
    }
    kotlin {
        target(
            "build-logic/src/**/*.kt",
            "core/src/**/*.kt",
            "site/src/**/*.kt",
        )
        val composeRulesKtlint =
            libs
                .findLibrary("compose-rules-ktlint")
                .map { it.orNull.toString() }
                .get()
        ktlint().customRuleSets(listOf(composeRulesKtlint))
    }
}
