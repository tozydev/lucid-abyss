package vn.id.tozydev.lucidabyss.styles

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.storage.createStorageKey
import com.varabyte.kobweb.browser.storage.getItem
import com.varabyte.kobweb.browser.storage.setItem
import com.varabyte.kobweb.core.AppGlobals
import com.varabyte.kobweb.core.isExporting
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.systemPreference
import kotlinx.browser.document
import kotlinx.browser.window
import vn.id.tozydev.lucidabyss.styles.ThemeMode.System

private val rootThemeModeState by lazy { mutableStateOf(ThemeMode.loadFromLocalStorage()) }
private val LocalThemeMode = compositionLocalOf { rootThemeModeState }

enum class ThemeMode {
    Light,
    Dark,
    System,
    ;

    /** Returns Light or Dark, depending on the current system preference */
    val actualMode: ThemeMode
        get() =
            when (this) {
                Light, Dark -> this
                System -> if (window.matchMedia("(prefers-color-scheme: dark)").matches) Dark else Light
            }

    val colorMode: ColorMode
        get() =
            when (this) {
                Light -> ColorMode.LIGHT
                Dark -> ColorMode.DARK
                System -> ColorMode.systemPreference
            }

    val cycle: ThemeMode
        get() =
            when (this) {
                Light -> Dark
                Dark -> System
                System -> Light
            }

    companion object {
        val currentState: MutableState<ThemeMode> @Composable get() = LocalThemeMode.current
    }
}

private val storageKey = ThemeMode.entries.createStorageKey("lucid-abyss:themeMode")

fun ThemeMode.Companion.loadFromLocalStorage(): ThemeMode = window.localStorage.getItem(storageKey) ?: System

fun ThemeMode.saveToLocalStorage() {
    window.localStorage.setItem(storageKey, this)
}

@InitSilk
fun initThemeMode(ctx: InitSilkContext) {
    ctx.config.initialColorMode = ThemeMode.loadFromLocalStorage().colorMode
    if (!AppGlobals.isExporting) {
        return
    }

    // Prevent flickering on the initial load because the main script is executed after HTML is rendered
    document.head?.appendChild(
        document.createElement("script").apply {
            // language="JavaScript"
            textContent =
                """
                const themeMode = localStorage.getItem("${storageKey.name}")
                let actualTheme = themeMode.toLowerCase()
                if (actualTheme !== "light" || actualTheme !== "dark") {
                    const prefersDark = window.matchMedia("(prefers-color-scheme: dark)").matches
                    actualTheme = prefersDark ? "dark" : "light"
                }

                document.documentElement.setAttribute("data-theme", actualTheme)
                """.trimIndent()
        },
    )
}
