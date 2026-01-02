package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.fa.FaDesktop
import com.varabyte.kobweb.silk.components.icons.fa.FaMoon
import com.varabyte.kobweb.silk.components.icons.fa.FaSun
import vn.id.tozydev.lucidabyss.styles.CircleButtonVariant
import vn.id.tozydev.lucidabyss.styles.ThemeMode

@Composable
fun ThemeButton(modifier: Modifier = Modifier) {
    var themeMode by ThemeMode.currentState
    Button(
        onClick = { themeMode = themeMode.cycle },
        modifier = modifier,
        variant = CircleButtonVariant,
    ) {
        when (themeMode.cycle) {
            ThemeMode.Light -> FaSun()
            ThemeMode.Dark -> FaMoon()
            ThemeMode.System -> FaDesktop()
        }
    }
}
