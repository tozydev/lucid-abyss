package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import js.promise.await
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement
import vn.id.tozydev.lucidabyss.utils.shiki.highlightCode
import vn.id.tozydev.lucidabyss.utils.tw
import web.navigator.navigator
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Composable
fun CodeBlock(
    code: String,
    modifier: Modifier = Modifier,
    lang: String? = null,
    showHeader: Boolean = false,
) {
    Figure(
        Modifier
            .tw("relative rounded-xl overflow-hidden border border-outline/30 shadow-md bg-surface-container-low not-prose my-4")
            .then(modifier)
            .toAttrs(),
    ) {
        if (showHeader) {
            Div({ tw("flex items-center gap-2 sm:gap-3 px-3 pt-3") }) {
                Div(
                    {
                        tw(
                            "flex items-center gap-2 px-2 py-1 bg-surface-container-lowest rounded-lg border border-outline/30 text-xs font-medium font-mono text-on-surface shadow-sm cursor-default select-none",
                        )
                    },
                ) {
                    Text(lang ?: "none")
                }
            }
        }

        Div({ tw("absolute top-3 right-3 z-10") }) {
            CopyButton(code)
        }
        CodeContent(code, lang)
    }
}

@Composable
private fun CodeContent(
    code: String,
    lang: String? = null,
) {
    var highlightedCode by remember { mutableStateOf("<pre><code>$code</code></pre>") }

    LaunchedEffect(code) {
        highlightedCode = highlightCode(code, lang ?: "none")
    }

    Div(
        {
            prop<HTMLDivElement, String>(
                { element, value -> element.innerHTML = value },
                highlightedCode,
            )
        },
    )
}

private val copyIconModifier = Modifier.tw("size-5 text-on-surface-variant/80")

@Composable
private fun CopyButton(code: String) {
    val scope = rememberCoroutineScope()
    var copied by remember { mutableStateOf(false) }

    Button(
        Modifier
            .tw("cursor-pointer active:scale-90 transition-transform")
            .ariaLabel("Copy code to clipboard")
            .onClick {
                scope.launch {
                    delay(100.milliseconds)
                    navigator.clipboard.writeTextAsync(code).await()
                    copied = true
                    delay(2.seconds)
                    copied = false
                }
            }.toAttrs(),
    ) {
        if (copied) {
            CheckIcon(copyIconModifier)
        } else {
            ContentCopyIcon(copyIconModifier)
        }
    }
}
