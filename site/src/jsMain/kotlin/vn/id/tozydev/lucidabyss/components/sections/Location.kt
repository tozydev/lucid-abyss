package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaLocationDot
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.extendedBy
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.ColumnIslandVariant
import vn.id.tozydev.lucidabyss.components.widgets.Island
import vn.id.tozydev.lucidabyss.styles.ColorVars
import vn.id.tozydev.lucidabyss.styles.TextSmStyle

val LocationIslandVariant =
    ColumnIslandVariant.extendedBy {
        base {
            Modifier
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
                .padding(1.cssRem)
        }
    }

val LocationIconStyle =
    CssStyle {
        base {
            Modifier
                .borderRadius(9999.px)
                .backgroundColor(ColorVars.Secondary.value())
                .size(3.cssRem)
                .display(DisplayStyle.Flex)
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
                .margin(bottom = 0.5.cssRem)
                .color(ColorVars.TextInverse.value())
        }
    }

@Composable
fun Location(modifier: Modifier = Modifier) {
    Island(
        modifier = modifier,
        variant = LocationIslandVariant,
    ) {
        Div(LocationIconStyle.toAttrs()) {
            FaLocationDot()
        }
        H4(Modifier.fontWeight(FontWeight.SemiBold).toAttrs()) {
            Text("Hồ Chí Minh, Việt Nam")
        }
        P(TextSmStyle.toModifier().fontWeight(FontWeight.Medium).toAttrs()) {
            Text("Đang học tại UTH")
        }
    }
}
