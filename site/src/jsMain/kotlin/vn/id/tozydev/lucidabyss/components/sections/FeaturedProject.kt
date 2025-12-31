package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.CSSPosition
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextTransform
import com.varabyte.kobweb.compose.css.functions.RadialGradient
import com.varabyte.kobweb.compose.css.functions.radialGradient
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRight
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.ColumnIslandVariant
import vn.id.tozydev.lucidabyss.components.widgets.Island

@Composable
fun FeaturedProject(modifier: Modifier = Modifier) {
    Island(
        modifier = modifier,
        variance = ColumnIslandVariant,
    ) {
        // todo cover background image
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(1.cssRem),
        ) {
            // todo create badge component
            Span(
                Modifier
                    .color(Colors.White)
                    .borderRadius(0.25.cssRem)
                    .border(1.px, LineStyle.Solid, Colors.White)
                    .fontSize(0.625.cssRem)
                    .lineHeight(0.75.cssRem)
                    .fontWeight(FontWeight.Bold)
                    .textTransform(TextTransform.Uppercase)
                    .padding(0.25.cssRem, 0.5.cssRem)
                    .backgroundColor(Colors.Gray)
                    .toAttrs(),
            ) {
                Text("Nổi bật")
            }
            Span(
                Modifier
                    .color(Colors.White)
                    .borderRadius(0.25.cssRem)
                    .border(1.px, LineStyle.Solid, Colors.White)
                    .fontSize(0.625.cssRem)
                    .lineHeight(0.75.cssRem)
                    .fontWeight(FontWeight.Bold)
                    .textTransform(TextTransform.Uppercase)
                    .padding(0.25.cssRem, 0.5.cssRem)
                    .backgroundImage(
                        radialGradient(
                            shape = RadialGradient.Shape.Circle(RadialGradient.Extent.FarthestSide),
                            position = CSSPosition.TopRight,
                        ) {
                            add(Color.rgb(0xE44857))
                            add(Color.rgb(0xC711E1), 50.4494.percent)
                            add(Color.rgb(0x7F52FF), 100.percent)
                        },
                    ).toAttrs(),
            ) {
                Text("Kotlin")
            }
        }
        Column {
            H3 {
                Text("lucid-abyss")
            }
            P {
                Text("lucid-abyss là một website blog cá nhân được xây dựng hoàn toàn bằng Kotlin và Kobweb.")
            }
            Link("#") {
                SpanText("Xem dự án...", Modifier.margin(right = 0.25.cssRem))
                FaArrowRight()
            }
        }
    }
}
