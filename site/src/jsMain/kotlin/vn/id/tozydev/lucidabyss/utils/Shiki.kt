package vn.id.tozydev.lucidabyss.utils

import js.objects.recordOf
import kotlinx.coroutines.await
import kotlin.js.Promise

suspend fun highlightCode(
    code: String,
    lang: String,
): String {
    val shiki = js("""import('shiki')""").unsafeCast<Promise<dynamic>>().await()
    return shiki
        .codeToHtml(
            code,
            recordOf("lang" to lang, "theme" to "one-dark-pro"),
        ).unsafeCast<Promise<String>>()
        .await()
}
