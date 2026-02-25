package vn.id.tozydev.lucidabyss.utils

import androidx.compose.runtime.*
import kotlinx.browser.window
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.sample
import org.w3c.dom.events.Event
import kotlin.time.Duration.Companion.milliseconds

data class ScrollingState(
    val isScrollingDown: Boolean,
    val currentScrollY: Double,
    val lastScrollY: Double,
)

@OptIn(FlowPreview::class)
@Composable
fun rememberScrollingState(): ScrollingState {
    var state by remember { mutableStateOf(ScrollingState(false, 0.0, 0.0)) }

    LaunchedEffect(Unit) {
        callbackFlow {
            val handleScroll = { _: Event ->
                trySend(window.scrollY)
                Unit
            }

            // Initial value
            trySend(window.scrollY)

            window.addEventListener("scroll", handleScroll)

            awaitClose {
                window.removeEventListener("scroll", handleScroll)
            }
        }.sample(100.milliseconds) // Throttle updates to ~10fps, sufficient for UI visibility toggles
            .collect { scrollY ->
                state =
                    state.copy(
                        currentScrollY = scrollY,
                        lastScrollY = scrollY,
                        isScrollingDown = scrollY > state.lastScrollY,
                    )
            }
    }
    return state
}
