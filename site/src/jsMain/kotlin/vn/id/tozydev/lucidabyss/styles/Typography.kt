package vn.id.tozydev.lucidabyss.styles

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import org.jetbrains.compose.web.css.*

val TextXsModifier =
    Modifier
        .fontSize(0.75.cssRem)
        .lineHeight(1.cssRem)

val TextXsStyle =
    CssStyle.base {
        TextXsModifier
    }

val TextSmModifier =
    Modifier
        .fontSize(0.875.cssRem)
        .lineHeight(1.25.cssRem)

val TextSmStyle =
    CssStyle.base {
        TextSmModifier
    }

val TextBaseModifier =
    Modifier
        .fontSize(1.cssRem)
        .lineHeight(1.5.cssRem)

val TextBaseStyle =
    CssStyle.base {
        TextBaseModifier
    }

val TextLgModifier =
    Modifier
        .fontSize(1.125.cssRem)
        .lineHeight(1.75.cssRem)

val TextLgStyle =
    CssStyle.base {
        TextLgModifier
    }

val TextXlModifier =
    Modifier
        .fontSize(1.75.cssRem)
        .lineHeight(2.25.cssRem)

val TextXlStyle =
    CssStyle.base {
        TextXlModifier
    }

val Text2XlModifier =
    Modifier
        .fontSize(2.cssRem)
        .lineHeight(2.5.cssRem)

val Text2XlStyle =
    CssStyle.base {
        Text2XlModifier
    }

val Text3XlModifier =
    Modifier
        .fontSize(2.25.cssRem)
        .lineHeight(2.75.cssRem)

val Text3XlStyle =
    CssStyle.base {
        Text3XlModifier
    }

val Text4XlModifier =
    Modifier
        .fontSize(2.8125.cssRem)
        .lineHeight(3.25.cssRem)

val Text4XlStyle =
    CssStyle.base {
        Text4XlModifier
    }
