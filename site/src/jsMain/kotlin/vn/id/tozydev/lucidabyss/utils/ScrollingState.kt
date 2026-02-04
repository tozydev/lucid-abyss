package vn.id.tozydev.lucidabyss.utils

import androidx.compose.runtime.*
import kotlinx.browser.window

data class ScrollingState(
    val isScrollingDown: Boolean,
    val currentScrollY: Double,
    val lastScrollY: Double,
)

@Composable
fun rememberScrollingState(): ScrollingState {
    var state by remember { mutableStateOf(ScrollingState(false, 0.0, 0.0)) }
    DisposableEffect(Unit) {
        val handleScroll = {
            val scrollY = window.scrollY
            state =
                state.copy(
                    currentScrollY = scrollY,
                    lastScrollY = scrollY,
                    isScrollingDown = scrollY > state.lastScrollY,
                )
        }

        window.addEventListener("scroll", { handleScroll() })

        onDispose {
            window.removeEventListener("scroll", { handleScroll() })
        }
    }
    return state
}
