package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.dom.ElementTarget
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.silk.components.icons.mdi.MdiAutoMode
import com.varabyte.kobweb.silk.components.icons.mdi.MdiDarkMode
import com.varabyte.kobweb.silk.components.icons.mdi.MdiLightMode
import com.varabyte.kobweb.silk.components.overlay.PopupPlacement
import com.varabyte.kobweb.silk.components.overlay.Tooltip
import vn.id.tozydev.lucidabyss.theme.ThemeMode

@Composable
fun ThemeButton(modifier: Modifier = Modifier) {
    var themeMode by ThemeMode.currentState
    IconButton(
        modifier = modifier,
        onClick = { themeMode = themeMode.cycle },
    ) {
        when (themeMode.cycle) {
            ThemeMode.Light -> MdiLightMode()
            ThemeMode.Dark -> MdiDarkMode()
            ThemeMode.System -> MdiAutoMode()
        }
    }
    val tooltipText =
        when (themeMode.cycle) {
            ThemeMode.Light -> "Switch to Light Mode"
            ThemeMode.Dark -> "Switch to Dark Mode"
            ThemeMode.System -> "Switch to System Mode"
        }
    Tooltip(ElementTarget.PreviousSibling, tooltipText, placement = PopupPlacement.Right)
}
