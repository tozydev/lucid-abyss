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
            "dev-server-plugin/*.gradle.kts",
            "site/*.gradle.kts",
        )
        ktlint()
    }
    kotlin {
        target(
            "build-logic/src/**/*.kt",
            "dev-server-plugin/src/**/*.kt",
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
