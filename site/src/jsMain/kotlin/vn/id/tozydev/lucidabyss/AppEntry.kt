package vn.id.tozydev.lucidabyss

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import vn.id.tozydev.lucidabyss.styles.AppStyleSheet
import vn.id.tozydev.lucidabyss.styles.ThemeMode
import vn.id.tozydev.lucidabyss.styles.saveToLocalStorage

@Suppress("VariableNeverRead", "AssignedValueIsNeverRead")
@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    Style(AppStyleSheet)
    SilkApp {
        val themeMode by ThemeMode.currentState
        var colorMode by ColorMode.currentState
        LaunchedEffect(themeMode) {
            themeMode.saveToLocalStorage()
            colorMode = themeMode.colorMode
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
