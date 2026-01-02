package vn.id.tozydev.lucidabyss.styles

import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.layer.SilkLayer
import com.varabyte.kobweb.silk.style.layer.add
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.cssClass
import org.jetbrains.compose.web.css.*
import vn.id.tozydev.lucidabyss.styles.ColorVars.BgCanvas
import vn.id.tozydev.lucidabyss.styles.ColorVars.BgContainer
import vn.id.tozydev.lucidabyss.styles.ColorVars.BgHighlight
import vn.id.tozydev.lucidabyss.styles.ColorVars.BgOverlay
import vn.id.tozydev.lucidabyss.styles.ColorVars.BgSurface
import vn.id.tozydev.lucidabyss.styles.ColorVars.FocusRing
import vn.id.tozydev.lucidabyss.styles.ColorVars.Outline
import vn.id.tozydev.lucidabyss.styles.ColorVars.OutlineVariant
import vn.id.tozydev.lucidabyss.styles.ColorVars.Primary
import vn.id.tozydev.lucidabyss.styles.ColorVars.Secondary
import vn.id.tozydev.lucidabyss.styles.ColorVars.StateHover
import vn.id.tozydev.lucidabyss.styles.ColorVars.StatePressed
import vn.id.tozydev.lucidabyss.styles.ColorVars.TextBody
import vn.id.tozydev.lucidabyss.styles.ColorVars.TextHeading
import vn.id.tozydev.lucidabyss.styles.ColorVars.TextInverse
import vn.id.tozydev.lucidabyss.styles.ColorVars.TextLabel
import vn.id.tozydev.lucidabyss.utils.rgb
import vn.id.tozydev.lucidabyss.utils.set

object ColorVars {
    val Primary by StyleVariable<CSSColorValue>()
    val Secondary by StyleVariable<CSSColorValue>()
    val BgCanvas by StyleVariable<CSSColorValue>()

    val BgSurface by StyleVariable<CSSColorValue>()
    val BgContainer by StyleVariable<CSSColorValue>()
    val BgHighlight by StyleVariable<CSSColorValue>()
    val BgOverlay by StyleVariable<CSSColorValue>()
    val TextHeading by StyleVariable<CSSColorValue>()

    val TextBody by StyleVariable<CSSColorValue>()
    val TextLabel by StyleVariable<CSSColorValue>()
    val TextInverse by StyleVariable<CSSColorValue>()
    val Outline by StyleVariable<CSSColorValue>()

    val OutlineVariant by StyleVariable<CSSColorValue>()
    val StateHover by StyleVariable<CSSColorValue>()

    val StatePressed by StyleVariable<CSSColorValue>()
    val FocusRing by StyleVariable<CSSColorValue>()
}

const val THEME_LAYER = "theme"

@InitSilk
fun initThemeColors(ctx: InitSilkContext) {
    ctx.stylesheet.apply {
        cssLayers.add(THEME_LAYER, after = SilkLayer.RESTRICTED_STYLES)
        layer(THEME_LAYER) {
            registerStyleBase(":root") {
                Modifier.styleModifier { initLightModeColors() }
            }
            registerStyleBase(".${ColorMode.DARK.cssClass}") {
                Modifier.styleModifier { initDarkModeColors() }
            }
        }
    }
}

context(_: StyleScope)
private fun initLightModeColors() {
    Primary set Color.rgb(0x4D7C5E)
    Secondary set Color.rgb(0x8CB096)

    BgCanvas set Color.rgb(0xF2F0E9)
    BgSurface set Color.rgb(0xFFFFFF)
    BgContainer set Color.rgb(0xE6E8E3)
    BgHighlight set Color.rgb(0xD4DCD6)
    BgOverlay set rgba(44, 54, 48, 0.4f)

    TextHeading set Color.rgb(0x2C3630)
    TextBody set Color.rgb(0x444F48)
    TextLabel set Color.rgb(0x757F78)
    TextInverse set Color.rgb(0xFFFFFF)

    Outline set Color.rgb(0xD1D6D3)
    OutlineVariant set Color.rgb(0xB0B8B4)

    Outline set Color.rgb(0xD1D6D3)
    OutlineVariant set Color.rgb(0xB0B8B4)

    StateHover set rgb(Primary, alpha = 0.08f)
    StatePressed set rgb(Primary, alpha = 0.2f)
    FocusRing set rgb(Primary, alpha = 0.4f)
}

context(_: StyleScope)
private fun initDarkModeColors() {
    Primary set Color.rgb(0x8CB096)
    Secondary set Color.rgb(0x558B6E)

    BgCanvas set Color.rgb(0x121413)
    BgSurface set Color.rgb(0x1E2321)
    BgContainer set Color.rgb(0x2A302C)
    BgHighlight set Color.rgb(0x38423B)
    BgOverlay set Color.rgba(0, 0, 0, 0.6f)

    TextHeading set Color.rgb(0xE6E8E6)
    TextBody set Color.rgb(0xBDC2BF)
    TextLabel set Color.rgb(0x88918C)
    TextInverse set Color.rgb(0x121413)

    Outline set Color.rgb(0x343B37)
    OutlineVariant set Color.rgb(0x454F4A)

    StateHover set rgb(Primary, alpha = 0.08f)
    StatePressed set rgb(Primary, alpha = 0.2f)
    FocusRing set rgb(Primary, alpha = 0.4f)
}
