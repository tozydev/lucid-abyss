package vn.id.tozydev.lucidabyss

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.w3c.dom.HTMLElement
import vn.id.tozydev.lucidabyss.styles.ThemeMode
import vn.id.tozydev.lucidabyss.styles.saveToLocalStorage

@Suppress("VariableNeverRead", "AssignedValueIsNeverRead")
@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    SilkApp {
        val themeMode by ThemeMode.currentState
        var colorMode by ColorMode.currentState
        DisposableEffect(themeMode) {
            themeMode.saveToLocalStorage()
            colorMode = themeMode.colorMode
            val element = document.documentElement.unsafeCast<HTMLElement>()
            element.setAttribute("data-theme", themeMode.actualMode.name.lowercase())
            onDispose {
                element.removeAttribute("data-theme")
            }
        }
        Surface(
            SmoothColorStyle
                .toModifier()
                .fillMaxWidth()
                .minHeight(100.vh),
        ) {
            content()
        }
    }
}
