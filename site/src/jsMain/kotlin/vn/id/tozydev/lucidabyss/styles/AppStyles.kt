package vn.id.tozydev.lucidabyss.styles

import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.layout.HorizontalDividerStyle
import com.varabyte.kobweb.silk.components.navigation.LinkStyle
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.SilkStylesheet
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.selectors.link
import com.varabyte.kobweb.silk.style.selectors.visited
import com.varabyte.kobweb.silk.theme.MutableSilkTheme
import com.varabyte.kobweb.silk.theme.modifyStyle
import com.varabyte.kobweb.silk.theme.modifyStyleBase
import org.jetbrains.compose.web.css.*
import vn.id.tozydev.lucidabyss.theme.toColorScheme

@InitSilk
fun initSiteStyles(ctx: InitSilkContext) {
    ctx.stylesheet.customizeGlobalStyles()
    ctx.theme.customizeWidgetStyles()
}

private fun SilkStylesheet.customizeGlobalStyles() {
    registerStyle("html") {
        cssRule(CSSMediaQuery.MediaFeature("prefers-reduced-motion", StylePropertyValue("no-preference"))) {
            Modifier.scrollBehavior(ScrollBehavior.Smooth)
        }
    }
    registerStyleBase("body") {
        Modifier
            .fontFamily("Inter", "sans-serif")
            .then(TypeBodyModifier)
            .styleModifier {
                property("-webkit-font-smoothing", "antialiased")
            }
    }
}

private fun MutableSilkTheme.customizeWidgetStyles() {
    modifyStyleBase(HorizontalDividerStyle) {
        Modifier.fillMaxWidth()
    }
    modifyStyle(LinkStyle) {
        val colorScheme = colorMode.toColorScheme()
        val colorModifier = Modifier.color(colorScheme.primary)
        link {
            colorModifier
        }
        visited {
            colorModifier
        }
    }
}
