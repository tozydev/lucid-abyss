package vn.id.tozydev.lucidabyss.styles

import com.varabyte.kobweb.compose.css.FontSize
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.VerticalAlign
import com.varabyte.kobweb.compose.css.autoLength
import com.varabyte.kobweb.compose.css.fontSize
import com.varabyte.kobweb.compose.css.fontWeight
import com.varabyte.kobweb.compose.css.layer
import com.varabyte.kobweb.compose.css.verticalAlign
import com.varabyte.kobweb.silk.style.layer.SilkLayer
import org.jetbrains.compose.web.css.*

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
