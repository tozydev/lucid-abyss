package vn.id.tozydev.lucidabyss

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.App
import kotlinx.browser.document
import org.w3c.dom.HTMLElement
import vn.id.tozydev.lucidabyss.styles.ThemeMode
import vn.id.tozydev.lucidabyss.styles.saveToLocalStorage

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    ThemeModeAware()
    content()
}

private const val DARK_MODE_CLASS = "dark"

@Composable
private fun ThemeModeAware() {
    val themeMode by ThemeMode.currentState
    DisposableEffect(themeMode) {
        themeMode.saveToLocalStorage()
        val element = document.documentElement.unsafeCast<HTMLElement>()
        element.classList.toggle(DARK_MODE_CLASS, themeMode.actualMode == ThemeMode.Dark)
        onDispose {
            element.classList.remove(DARK_MODE_CLASS)
        }
    }
}
