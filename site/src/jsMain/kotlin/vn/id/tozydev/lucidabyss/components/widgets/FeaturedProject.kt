package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRightLong
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.models.Constants
import vn.id.tozydev.lucidabyss.styles.ColorVars
import vn.id.tozydev.lucidabyss.styles.TextLgStyle
import vn.id.tozydev.lucidabyss.utils.kotlinGradient

@Composable
fun FeaturedProject(modifier: Modifier = Modifier) {
    Island(
        modifier = modifier,
        variant = ColumnIslandVariant,
    ) {
        Div(Modifier.flex(1).toAttrs())
        Row(
            modifier = Modifier.margin(bottom = 0.75.cssRem),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(0.5.cssRem),
        ) {
            Badge(
                Modifier
                    .setVariable(BadgeVars.Color, ColorVars.TextInverse.value())
                    .setVariable(BadgeVars.BackgroundColor, ColorVars.Primary.value()),
            ) {
                Text("Nổi bật")
            }
            Badge(
                Modifier
                    .setVariable(BadgeVars.Color, ColorVars.TextInverse.value())
                    .backgroundImage(kotlinGradient()),
            ) {
                Text("Kotlin")
            }
            Badge(
                Modifier
                    .setVariable(BadgeVars.Color, ColorVars.TextInverse.value())
                    .setVariable(BadgeVars.BackgroundColor, Color.rgb(0x0e80f2)),
            ) {
                Text("Kobweb")
            }
        }
        Column {
            H3(
                TextLgStyle
                    .toModifier()
                    .color(ColorVars.TextHeading.value())
                    .fontWeight(FontWeight.SemiBold)
                    .margin(bottom = 0.5.cssRem)
                    .toAttrs(),
            ) {
                Text("lucid-abyss")
            }
            P(
                Modifier
                    .margin(bottom = 1.cssRem)
                    .toAttrs(),
            ) {
                Text("lucid-abyss là một website blog cá nhân được xây dựng hoàn toàn bằng Kotlin và Kobweb.")
            }
            Link(
                path = Constants.LUCID_ABYSS_GITHUB_URL,
                variant = UndecoratedLinkVariant,
            ) {
                SpanText("Xem dự án", Modifier.margin(right = 0.5.cssRem))
                FaArrowRightLong()
            }
        }
    }
}
