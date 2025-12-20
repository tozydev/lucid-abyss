package vn.id.tozydev.lucidabyss

import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.navigation.LinkStyle
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.selectors.link
import com.varabyte.kobweb.silk.style.selectors.visited
import com.varabyte.kobweb.silk.theme.modifyStyle
import org.jetbrains.compose.web.css.*
import vn.id.tozydev.lucidabyss.theme.toColorScheme

@InitSilk
fun initSiteStyles(ctx: InitSilkContext) {
    with(ctx.stylesheet) {
        registerStyle("html") {
            cssRule(CSSMediaQuery.MediaFeature("prefers-reduced-motion", StylePropertyValue("no-preference"))) {
                Modifier.scrollBehavior(ScrollBehavior.Smooth)
            }
        }
        registerStyleBase("body") {
            Modifier
                .fontFamily("Inter", "sans-serif")
                .fontSize(16.px)
                .lineHeight(1.5)
                .styleModifier {
                    property("-webkit-font-smoothing", " antialiased")
                }
        }
    }

    with(ctx.theme) {
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
}

val TypeDisplayLargeStyle =
    CssStyle.base {
        Modifier
            .fontSize(3.5.cssRem)
            .lineHeight(4.cssRem)
            .fontWeight(400)
            .letterSpacing((-0.25).px)
    }

val TypeDisplayMediumStyle =
    CssStyle.base {
        Modifier
            .fontSize(2.8.cssRem)
            .lineHeight(3.25.cssRem)
            .fontWeight(400)
            .letterSpacing(0.px)
    }
val TypeHeadlineLargeStyle =
    CssStyle.base {
        Modifier
            .fontSize(2.cssRem)
            .lineHeight(2.5.cssRem)
            .fontWeight(400)
            .letterSpacing(0.px)
    }
val TypeHeadlineMediumStyle =
    CssStyle.base {
        Modifier
            .fontSize(1.75.cssRem)
            .lineHeight(2.25.cssRem)
            .fontWeight(400)
            .letterSpacing(0.px)
    }
val TypeHeadlineSmallStyle =
    CssStyle.base {
        Modifier
            .fontSize(1.5.cssRem)
            .lineHeight(2.cssRem)
            .fontWeight(400)
            .letterSpacing(0.px)
    }
val TypeTitleLargeStyle =
    CssStyle.base {
        Modifier
            .fontSize(1.375.cssRem)
            .lineHeight(1.75.cssRem)
            .fontWeight(400)
            .letterSpacing(0.px)
    }
val TypeTitleMediumStyle =
    CssStyle.base {
        Modifier
            .fontSize(1.cssRem)
            .lineHeight(1.5.cssRem)
            .fontWeight(500)
            .letterSpacing(0.15.px)
    }
val TypeBodyLargeStyle =
    CssStyle.base {
        Modifier
            .fontSize(1.cssRem)
            .lineHeight(1.5.cssRem)
            .fontWeight(400)
            .letterSpacing(0.5.px)
    }
val TypeBodyMediumStyle =
    CssStyle.base {
        Modifier
            .fontSize(0.875.cssRem)
            .lineHeight(1.25.cssRem)
            .fontWeight(400)
            .letterSpacing(0.25.px)
    }
val TypeLabelLargeStyle =
    CssStyle.base {
        Modifier
            .fontSize(0.875.cssRem)
            .lineHeight(1.25.cssRem)
            .fontWeight(500)
            .letterSpacing(0.1.px)
    }
val TypeLabelMediumStyle =
    CssStyle.base {
        Modifier
            .fontSize(0.75.cssRem)
            .lineHeight(1.cssRem)
            .fontWeight(500)
            .letterSpacing(0.5.px)
    }
