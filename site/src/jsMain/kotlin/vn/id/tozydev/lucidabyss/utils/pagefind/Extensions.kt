package vn.id.tozydev.lucidabyss.utils.pagefind

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import org.jetbrains.compose.web.attributes.AttrsScope

private const val DATA_PAGEFIND_META = "data-pagefind-meta"

fun Modifier.pagefindMeta(key: String) = attr(DATA_PAGEFIND_META, key)

fun AttrsScope<*>.pagefindMeta(key: String) = attr(DATA_PAGEFIND_META, key)

private const val DATA_PAGEFIND_BODY = "data-pagefind-body"

fun AttrsScope<*>.pagefindBody() = attr(DATA_PAGEFIND_BODY, "")
