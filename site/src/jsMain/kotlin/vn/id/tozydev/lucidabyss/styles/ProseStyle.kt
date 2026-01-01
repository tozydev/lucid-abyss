package vn.id.tozydev.lucidabyss.styles

import com.varabyte.kobweb.compose.css.CSSColor
import com.varabyte.kobweb.compose.css.Content
import com.varabyte.kobweb.compose.css.FontSize
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.LineHeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.cssRule
import org.jetbrains.compose.web.css.*

object ProseVars {
    val ColorBodyVar by StyleVariable<Color>(Color.rgb(0x364153))
    val ColorHeadingsVar by StyleVariable<Color>(Color.rgb(0x101828))
    val ColorLinkVar by StyleVariable<Color>(Color.rgb(0x101828))
    val ColorCodeVar by StyleVariable<Color>(Color.rgb(0x101828))
    val ColorPreCodeVar by StyleVariable<Color>(Color.rgb(0xe5e7eb))
    val ColorPreBgVar by StyleVariable<Color>(Color.rgb(0x1e2939))
    val ColorBVar by StyleVariable<Color>(Color.rgb(0x101828))
    val ColorQuotesVar by StyleVariable<Color>(Color.rgb(0x101828))
    val ColorQuotesBordersVar by StyleVariable<Color>(Color.rgb(0xe5e7eb))
}

val ProseStyle =
    CssStyle {
        base {
            Modifier
                .fontSize(1.cssRem)
                .lineHeight(1.75)
                .color(ProseVars.ColorBodyVar.value())
        }

        cssRule(" > :first-child") {
            Modifier.margin(top = 0.px)
        }

        cssRule(" > :last-child") {
            Modifier.margin(bottom = 0.px)
        }

        Breakpoint.MD {
            Modifier
                .fontSize(1.125.cssRem)
                .lineHeight(1.77778)
        }

        // Paragraphs

        cssRule(" p") {
            Modifier
                .margin(topBottom = 1.25.em)
        }

        cssRule(Breakpoint.MD, " p") {
            Modifier
                .margin(topBottom = 1.3333333.em)
        }

        cssRule(" b") {
            Modifier
                .color(ProseVars.ColorBVar.value())
                .fontWeight(FontWeight.SemiBold)
        }

        // Headings

        cssRule(" h2") {
            Modifier
                .color(ProseVars.ColorHeadingsVar.value())
                .fontSize(1.5.em)
                .fontWeight(FontWeight.Bold)
                .lineHeight(1.3333333)
                .margin(top = 2.em, bottom = 1.em)
        }
        cssRule(" h3") {
            Modifier
                .color(ProseVars.ColorHeadingsVar.value())
                .fontSize(1.25.em)
                .fontWeight(FontWeight.SemiBold)
                .lineHeight(1.6)
                .margin(top = 1.6.em, bottom = 0.6.em)
        }
        cssRule(" h4") {
            Modifier
                .color(ProseVars.ColorHeadingsVar.value())
                .lineHeight(1.5)
                .fontWeight(FontWeight.SemiBold)
                .margin(top = 1.5.em, bottom = 0.5.em)
        }
        cssRule(" h2 + *") {
            Modifier.margin(top = 0.px)
        }
        cssRule(" h3 + *") {
            Modifier.margin(top = 0.px)
        }
        cssRule(" h4 + *") {
            Modifier.margin(top = 0.px)
        }
        cssRule(Breakpoint.MD, " h2") {
            Modifier
                .fontSize(1.66667.em)
                .lineHeight(1.3333333)
                .margin(top = 1.86667.em, bottom = 1.06667.em)
        }
        cssRule(Breakpoint.MD, " h3") {
            Modifier
                .fontSize(1.33333.em)
                .lineHeight(1.5)
                .margin(top = 1.66667.em, bottom = 0.666667.em)
        }
        cssRule(Breakpoint.MD, " h4") {
            Modifier
                .lineHeight(1.55556)
                .margin(top = 1.77778.em, bottom = 0.444444.em)
        }

        // Lists

        val listModifier =
            Modifier
                .paddingInline { start(1.625.em) }
                .margin(topBottom = 1.25.em)
        cssRule(" ol") {
            listModifier
        }
        cssRule(" ul") {
            listModifier
        }
        cssRule(" li") {
            Modifier
                .paddingInline { start(0.375.em) }
                .margin(topBottom = 0.5.em)
        }

        val mdListModifier =
            Modifier
                .paddingInline { start(1.55556.em) }
                .margin(topBottom = 1.33333.em)
        cssRule(Breakpoint.MD, " ol") {
            mdListModifier
        }
        cssRule(Breakpoint.MD, " ul") {
            mdListModifier
        }
        cssRule(Breakpoint.MD, " li") {
            Modifier
                .paddingInline { start(0.444444.em) }
                .margin(topBottom = 0.666667.em)
        }

        // Links

        cssRule(" a") {
            Modifier
                .color(ProseVars.ColorLinkVar.value())
                .fontWeight(FontWeight.Medium)
                .textDecorationLine(TextDecorationLine.Underline)
        }
        val inheritColorModifier =
            Modifier
                .color("inherit".unsafeCast<CSSColor>())
        cssRule(" a b") {
            inheritColorModifier
        }

        // Inline code
        cssRule(" code") {
            Modifier
                .color(ProseVars.ColorCodeVar.value())
                .fontSize(.875.em)
                .fontWeight(FontWeight.SemiBold)
        }
        cssRule(Breakpoint.MD, " code") {
            Modifier
                .fontSize(.888889.em)
        }
        cssRule(" a code") {
            inheritColorModifier
        }
        cssRule(" h2 code") {
            inheritColorModifier.fontSize(.875.em)
        }
        cssRule(" h3 code") {
            inheritColorModifier.fontSize(.9.em)
        }
        cssRule(" h4 code") {
            inheritColorModifier
        }

        // Code blocks
        val sharedModifier2 =
            Modifier
                .content(Content.None)
        cssRule(" pre") {
            Modifier
                .color(ProseVars.ColorPreCodeVar.value())
                .backgroundColor(ProseVars.ColorPreBgVar.value())
                .borderRadius(0.375.cssRem)
                .fontSize(0.875.em)
                .fontWeight(400)
                .lineHeight(1.71429)
                .overflow { x(Overflow.Auto) }
                .margin(topBottom = 1.71429.em)
                .padding(topBottom = 0.857143.em, leftRight = 1.14286.em)
        }
        cssRule(" pre code") {
            Modifier
                .fontWeight(FontWeight.Inherit)
                .color("inherit".unsafeCast<CSSColor>())
                .fontSize(FontSize.Inherit)
                .fontFamily("inherit")
                .lineHeight(LineHeight.Inherit)
                .backgroundColor(Colors.Transparent)
                .border { width(0.px) }
                .borderRadius(0.px)
                .padding(0.px)
        }
        cssRule(" pre code::before") {
            sharedModifier2
        }
        cssRule(" pre code::after") {
            sharedModifier2
        }
        cssRule(Breakpoint.MD, " pre") {
            Modifier
                .borderRadius(0.375.cssRem)
                .fontSize(0.888889.em)
                .lineHeight(1.75)
                .margin(topBottom = 2.em)
                .padding(topBottom = 1.em, leftRight = 1.5.em)
        }

        // Images and Videos

        val mediaModifier =
            Modifier
                .margin(topBottom = 2.em)
        cssRule(" img") {
            mediaModifier
        }
        cssRule(" picture") {
            mediaModifier.display(DisplayStyle.Block)
        }
        cssRule(" video") {
            mediaModifier
        }
        cssRule(" picture > img") {
            Modifier
                .margin(topBottom = 0.px)
        }
        val mdMediaModifier =
            Modifier
                .margin(topBottom = 1.77778.em)
        cssRule(Breakpoint.MD, " img") {
            mdMediaModifier
        }
        cssRule(Breakpoint.MD, " picture") {
            mdMediaModifier
        }
        cssRule(Breakpoint.MD, " video") {
            mdMediaModifier
        }

        // Blockquotes

        cssRule(" blockquote") {
            Modifier
                .color(ProseVars.ColorQuotesVar.value())
                .fontWeight(FontWeight.Medium)
                .fontStyle(FontStyle.Italic)
                .borderLeft(0.25.cssRem, LineStyle.Solid, ProseVars.ColorQuotesBordersVar.value())
                .paddingInline { start(1.em) }
                .margin(topBottom = 1.6.em)
                .styleModifier {
                    property("quotes", "\"“\" \"”\" \"‘\" \"’\"")
                }
        }
        cssRule(" blockquote p:first-of-type::before") {
            Modifier
                .content(Content.OpenQuote)
        }
        cssRule(" blockquote p:last-of-type::after") {
            Modifier
                .content(Content.CloseQuote)
        }
        cssRule(" blockquote strong") {
            inheritColorModifier
        }
        cssRule(" blockquote code") {
            inheritColorModifier
        }
        cssRule(Breakpoint.MD, " blockquote") {
            Modifier
                .paddingInline { start(1.em) }
                .margin(topBottom = 1.66667.em)
        }
    }
