package vn.id.tozydev.lucidabyss

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.KobwebComposeStyles
import com.varabyte.kobweb.core.App
import kotlinx.browser.document
import org.w3c.dom.HTMLElement
import vn.id.tozydev.lucidabyss.styles.ThemeMode
import vn.id.tozydev.lucidabyss.styles.saveToLocalStorage

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    KobwebComposeStyles()
    ThemeModeAware()
    content()
}

private const val DATA_THEME_ATTR = "data-theme"

@Composable
private fun ThemeModeAware() {
    val themeMode by ThemeMode.currentState
    DisposableEffect(themeMode) {
        themeMode.saveToLocalStorage()
        val element = document.documentElement.unsafeCast<HTMLElement>()
        element.setAttribute(DATA_THEME_ATTR, themeMode.actualMode.name.lowercase())
        onDispose {
            element.removeAttribute(DATA_THEME_ATTR)
        }
    }
}
