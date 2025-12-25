package vn.id.tozydev.lucidabyss.styles

import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.OverflowWrap
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import org.jetbrains.compose.web.css.*

val TypeDisplayModifier =
    Modifier
        .fontSize(3.5.cssRem)
        .lineHeight(4.cssRem)
        .fontWeight(FontWeight.Medium)

val TypeDisplayStyle =
    CssStyle.base {
        TypeDisplayModifier
    }

val TypeHeadlineModifier =
    Modifier
        .fontSize(2.cssRem)
        .lineHeight(2.5.cssRem)
        .fontWeight(FontWeight.Medium)

val TypeHeadlineStyle =
    CssStyle.base {
        TypeHeadlineModifier
    }

val TypeTitleModifier =
    Modifier
        .fontSize(1.375.cssRem)
        .lineHeight(1.75.cssRem)
        .fontWeight(FontWeight.Medium)

val TypeTitleStyle =
    CssStyle.base {
        TypeTitleModifier
    }

val TypeBodyModifier =
    Modifier
        .fontSize(16.px)
        .lineHeight(1.75.cssRem)
        .overflowWrap(OverflowWrap.BreakWord)

val TypeBodyStyle =
    CssStyle.base {
        TypeBodyModifier
            .fontSize(1.cssRem)
    }

val TypeLabelModifier =
    Modifier
        .fontSize(0.875.cssRem)
        .lineHeight(1.25.cssRem)
        .fontWeight(FontWeight.Medium)

val TypeLabelStyle =
    CssStyle.base {
        TypeLabelModifier
    }
