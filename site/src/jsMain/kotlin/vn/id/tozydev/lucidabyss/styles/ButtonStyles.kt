package vn.id.tozydev.lucidabyss.styles

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.extendedBy
import org.jetbrains.compose.web.css.*
import vn.id.tozydev.lucidabyss.utils.rgb

val PrimaryButtonVariant =
    ButtonStyle.addVariant {
        base {
            Modifier
                .setVariable(ButtonVars.Color, ColorVars.TextInverse.value())
                .setVariable(ButtonVars.BackgroundDefaultColor, ColorVars.Primary.value())
                .setVariable(ButtonVars.BackgroundHoverColor, rgb(ColorVars.TextInverse, alpha = 0.15f))
                .setVariable(ButtonVars.BackgroundPressedColor, rgb(ColorVars.TextInverse, alpha = 0.25f))
        }
    }

val CircleButtonVariant =
    ButtonStyle.addVariant {
        base {
            Modifier
                .borderRadius(50.percent)
                .size(3.cssRem)
                .padding(0.cssRem)
        }
    }

val IconButtonVariant =
    CircleButtonVariant.extendedBy {
        base {
            Modifier.backgroundColor(Colors.Transparent)
        }
    }
