package vn.id.tozydev.lucidabyss.utils.shiki

import js.import.importAsync
import js.objects.Record
import js.objects.buildRecord
import js.objects.recordOf
import js.objects.unsafeJso
import js.promise.Promise
import js.promise.await

private val bundledLanguages: Record<String, () -> Promise<Any>> =
    buildRecord {
        this["kotlin"] = { importAsync("@shikijs/langs/kotlin") }
        this["java"] = { importAsync("@shikijs/langs/java") }
        this["shell"] = { importAsync("@shikijs/langs/shell") }
    }
private val bundledThemes: Record<String, () -> Promise<Any>> =
    buildRecord {
        this["one-light"] = { importAsync("@shikijs/themes/one-light") }
        this["one-dark-pro"] = { importAsync("@shikijs/themes/one-dark-pro") }
    }

private val codeToHtml =
    run {
        val highlighterCreator =
            createBundledHighlighter(
                unsafeJso {
                    langs = bundledLanguages
                    themes = bundledThemes
                    engine = {
                        importAsync<dynamic>("@shikijs/engine-javascript").then { it.createJavaScriptRegexEngine() }
                    }
                },
            )

        createSingletonShorthands(highlighterCreator).codeToHtml
    }

private val defaultThemes =
    recordOf(
        "light" to "one-light",
        "dark" to "one-dark-pro",
    )

suspend fun highlightCode(
    code: String,
    lang: String,
): String =
    codeToHtml(
        code,
        unsafeJso {
            this.lang = lang
            this.themes = defaultThemes
            this.defaultColor = false
        },
    ).unsafeCast<Promise<String>>().await()
