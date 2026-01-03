package vn.id.tozydev.lucidabyss.styles

import com.varabyte.kobweb.compose.css.BoxShadow
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontSize
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.OverflowWrap
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.css.VerticalAlign
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.css.autoLength
import com.varabyte.kobweb.compose.css.fontSize
import com.varabyte.kobweb.compose.css.fontWeight
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.css.layer
import com.varabyte.kobweb.compose.css.verticalAlign
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.components.layout.DividerVars
import com.varabyte.kobweb.silk.components.layout.HorizontalDividerStyle
import com.varabyte.kobweb.silk.components.navigation.LinkStyle
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.SilkStylesheet
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.layer.SilkLayer
import com.varabyte.kobweb.silk.style.selectors.active
import com.varabyte.kobweb.silk.style.selectors.ariaDisabled
import com.varabyte.kobweb.silk.style.selectors.focusVisible
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.selectors.link
import com.varabyte.kobweb.silk.style.selectors.not
import com.varabyte.kobweb.silk.style.selectors.visited
import com.varabyte.kobweb.silk.style.vars.animation.TransitionDurationVars
import com.varabyte.kobweb.silk.style.vars.color.BackgroundColorVar
import com.varabyte.kobweb.silk.style.vars.color.BorderColorVar
import com.varabyte.kobweb.silk.style.vars.color.ColorVar
import com.varabyte.kobweb.silk.style.vars.color.FocusOutlineColorVar
import com.varabyte.kobweb.silk.style.vars.color.PlaceholderColorVar
import com.varabyte.kobweb.silk.style.vars.size.BorderRadiusVars
import com.varabyte.kobweb.silk.theme.MutableSilkTheme
import com.varabyte.kobweb.silk.theme.modifyStyle
import com.varabyte.kobweb.silk.theme.modifyStyleBase
import org.jetbrains.compose.web.css.*
import vn.id.tozydev.lucidabyss.components.widgets.BadgeVars
import vn.id.tozydev.lucidabyss.components.widgets.IslandVars
import vn.id.tozydev.lucidabyss.styles.ColorVars.BgCanvas
import vn.id.tozydev.lucidabyss.styles.ColorVars.BgContainer
import vn.id.tozydev.lucidabyss.styles.ColorVars.BgHighlight
import vn.id.tozydev.lucidabyss.styles.ColorVars.BgSurface
import vn.id.tozydev.lucidabyss.styles.ColorVars.FocusRing
import vn.id.tozydev.lucidabyss.styles.ColorVars.Outline
import vn.id.tozydev.lucidabyss.styles.ColorVars.OutlineVariant
import vn.id.tozydev.lucidabyss.styles.ColorVars.Primary
import vn.id.tozydev.lucidabyss.styles.ColorVars.StateHover
import vn.id.tozydev.lucidabyss.styles.ColorVars.StatePressed
import vn.id.tozydev.lucidabyss.styles.ColorVars.TextBody
import vn.id.tozydev.lucidabyss.styles.ColorVars.TextLabel
import vn.id.tozydev.lucidabyss.utils.rgb
import vn.id.tozydev.lucidabyss.utils.set

@InitSilk
fun initSiteStyles(ctx: InitSilkContext) {
    ctx.theme.customizeWidgetStyles()
    ctx.stylesheet.customizeGlobalStyles()
    context(ctx) {
        initWidgetColors()
    }
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
            .fontSize(16.px)
            .lineHeight(1.5.cssRem)
            .overflowWrap(OverflowWrap.BreakWord)
            .styleModifier {
                property("-webkit-font-smoothing", "antialiased")
            }
    }
}

private fun MutableSilkTheme.customizeWidgetStyles() {
    modifyStyleBase(HorizontalDividerStyle) {
        Modifier
            .fillMaxWidth()
    }
    modifyStyle(LinkStyle) {
        val colorModifier = Modifier.color(Primary.value())
        base {
            colorModifier
        }
        hover {
            Modifier
                .color(rgb(Primary, alpha = 0.8f))
                .textDecorationLine(TextDecorationLine.Underline)
        }
        link {
            colorModifier
        }
        visited {
            colorModifier
        }
    }
    replaceStyle(ButtonStyle) {
        base {
            Modifier
                .color(ButtonVars.Color.value())
                .backgroundColor(ButtonVars.BackgroundDefaultColor.value())
                .lineHeight(1.2)
                .height(ButtonVars.Height.value())
                .minWidth(ButtonVars.Height.value()) // A button should get no more squashed than square / rectangular
                .fontSize(ButtonVars.FontSize.value())
                .fontWeight(FontWeight.SemiBold)
                .whiteSpace(WhiteSpace.NoWrap)
                .padding(leftRight = ButtonVars.PaddingHorizontal.value())
                .verticalAlign(VerticalAlign.Middle)
                .borderRadius(BorderRadiusVars.MD.value())
                .border { width(0.px) }
                .userSelect(UserSelect.None) // No selecting text within buttons
                .transition {
                    property(TransitionProperty.All)
                    duration(ButtonVars.ColorTransitionDuration.value())
                    timingFunction(TransitionTimingFunction.cubicBezier(0.4, 0.0, 0.2, 1.0)) // todo reusable transition
                }
        }

        (hover + not(ariaDisabled)) {
            Modifier
                .backgroundImage(
                    linearGradient(
                        ButtonVars.BackgroundHoverColor.value(),
                        ButtonVars.BackgroundHoverColor.value(),
                    ),
                ).cursor(Cursor.Pointer)
        }

        (focusVisible + not(ariaDisabled)) {
            Modifier
                .outline(2.px, LineStyle.Solid, Colors.Transparent)
                .boxShadow(spreadRadius = 0.1875.cssRem, color = ButtonVars.BackgroundFocusColor.value())
        }

        (active + not(ariaDisabled)) {
            Modifier.backgroundImage(
                linearGradient(
                    ButtonVars.BackgroundPressedColor.value(),
                    ButtonVars.BackgroundPressedColor.value(),
                ),
            )
        }
    }
}

context(ctx: InitSilkContext)
private fun initWidgetColors() {
    ctx.stylesheet.registerStyleBase(":root") {
        Modifier.styleModifier {
            BackgroundColorVar set BgCanvas.value()
            ColorVar set TextBody.value()
            BorderColorVar set Outline.value()
            FocusOutlineColorVar set FocusRing.value()
            PlaceholderColorVar set TextLabel.value()

            IslandVars.BackgroundColorVar set BgSurface.value()
            IslandVars.TextColorVar set TextBody.value()
            IslandVars.BorderColorVar set Outline.value()
            IslandVars.BorderHoverColorVar set Primary.value()
            IslandVars.BoxShadowVar set
                BoxShadow.of(
                    offsetX = 0.px,
                    offsetY = 1.px,
                    blurRadius = 3.px,
                    spreadRadius = 0.px,
                    color = Colors.Black.copyf(alpha = .1f),
                )
            IslandVars.BoxShadowHoverVar set
                BoxShadow.of(
                    offsetX = 0.px,
                    offsetY = 4.px,
                    blurRadius = 20.px,
                    spreadRadius = 0.px,
                    color = Colors.Black.copyf(alpha = .15f),
                )

            BadgeVars.Color set TextLabel.value()
            BadgeVars.BackgroundColor set BgHighlight.value()

            BorderRadiusVars.XS set 0.25.cssRem
            BorderRadiusVars.SM set 0.5.cssRem
            BorderRadiusVars.MD set 0.75.cssRem
            BorderRadiusVars.LG set 1.5.cssRem

            ButtonVars.Color set TextBody.value()
            ButtonVars.BackgroundDefaultColor set BgContainer.value()
            ButtonVars.BackgroundHoverColor set StateHover.value()
            ButtonVars.BackgroundPressedColor set StatePressed.value()
            ButtonVars.BackgroundFocusColor set FocusRing.value()
            ButtonVars.ColorTransitionDuration set TransitionDurationVars.Fast.value()

            DividerVars.Color set OutlineVariant.value()
        }
    }
}

object AppStyleSheet : StyleSheet() {
    init {
        // CSS Rules taken from https://github.com/tailwindlabs/tailwindcss/blob/main/packages/tailwindcss/preflight.css
        layer(SilkLayer.BASE.layerName) {
            "*, ::before, ::after" {
                boxSizing("border-box")
                margin(0.px)
                padding(0.px)
                border(0.px, LineStyle.Solid)
            }

            "h1, h2, h3, h4, h5, h6" {
                fontSize(FontSize.Inherit)
                fontWeight(FontWeight.Inherit)
            }

            "img, svg, video, canvas, audio, iframe, embed, object" {
                display(DisplayStyle.Block)
                verticalAlign(VerticalAlign.Middle)
            }

            "img, video" {
                maxWidth(100.percent)
                height(autoLength)
            }

            "[hidden]:where(:not([hidden='until-found']))" { display(DisplayStyle.None) }
        }
    }
}
