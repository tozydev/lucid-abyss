@file:JsModule("@shikijs/core")

package vn.id.tozydev.lucidabyss.utils.shiki

import js.objects.Record
import kotlin.js.Promise

external interface HighlighterOptions {
    var langs: dynamic
    var themes: dynamic
    var engine: () -> dynamic
}

external fun createBundledHighlighter(options: HighlighterOptions): () -> Promise<dynamic>

external fun createSingletonShorthands(highlighterCreator: () -> Promise<dynamic>): SingletonShorthands

external interface CodeToHastOptions {
    var data: Record<String, dynamic>
    var lang: String
    var themes: Record<String, String>
    var defaultColor: Boolean
}

external interface SingletonShorthands {
    val codeToHtml: (code: String, options: CodeToHastOptions) -> Promise<String>
}
