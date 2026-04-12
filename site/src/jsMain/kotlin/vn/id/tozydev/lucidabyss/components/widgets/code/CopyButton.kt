package vn.id.tozydev.lucidabyss.components.widgets.code

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import js.promise.await
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.CheckIcon
import vn.id.tozydev.lucidabyss.components.widgets.ContentCopyIcon
import vn.id.tozydev.lucidabyss.utils.tw
import web.navigator.navigator
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

private val copyIconModifier = Modifier.tw("size-5 text-on-surface-variant/80")

@Composable
fun CopyButton(
    code: String,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    var copied by remember { mutableStateOf(false) }

    Div(Modifier.tw("absolute top-3 right-3 z-10").then(modifier).toAttrs()) {
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
}
