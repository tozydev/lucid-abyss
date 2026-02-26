package vn.id.tozydev.lucidabyss.components.blog

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.thenUnless
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.components.icons.fa.FaBluesky
import com.varabyte.kobweb.silk.components.icons.fa.FaFacebook
import com.varabyte.kobweb.silk.components.icons.fa.FaLink
import com.varabyte.kobweb.silk.components.icons.fa.FaLinkedin
import js.promise.await
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw
import web.navigator.navigator
import web.window.window
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun PostShare(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw(
                "card card-border bg-base-100 hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] transition-all duration-300",
            ).then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body items-center text-center lg:text-start lg:items-start") }) {
            H3({ tw("card-title text-base mb-2") }) {
                Text(Strings.widget.share.post.title)
            }

            Div({ tw("flex items-center gap-4 lg:gap-2") }) {
                Anchor(
                    href = "",
                    attrs = { tw("btn btn-circle") },
                ) {
                    FaFacebook()
                }
                Anchor(
                    href = "",
                    attrs = { tw("btn btn-circle") },
                ) {
                    FaLinkedin()
                }
                Anchor(
                    href = "",
                    attrs = { tw("btn btn-circle") },
                ) {
                    FaBluesky()
                }
                CopyLinkButton()
            }
        }
    }
}

@Composable
private fun CopyLinkButton() {
    val coroutineScope = rememberCoroutineScope()
    var isClicked by remember { mutableStateOf(false) }
    Div(
        Modifier
            .tw("tooltip")
            .thenIf(isClicked) {
                Modifier
                    .tw("tooltip-open")
                    .attr("data-tip", Strings.widget.share.post.linkCopied)
            }.thenUnless(isClicked) {
                Modifier.attr("data-tip", Strings.widget.share.post.link)
            }.toAttrs(),
    ) {
        Anchor(
            href = "",
            attrs = {
                tw("btn btn-circle")
                onClick {
                    coroutineScope.launch {
                        navigator.clipboard.writeTextAsync(window.location.href).await()
                        isClicked = true
                        launch {
                            delay(500.milliseconds)
                            isClicked = false
                        }
                    }
                }
            },
        ) {
            FaLink()
        }
    }
}
