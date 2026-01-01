package vn.id.tozydev.lucidabyss.utils

import com.varabyte.kobweb.compose.css.CSSPosition
import com.varabyte.kobweb.compose.css.functions.RadialGradient
import com.varabyte.kobweb.compose.css.functions.radialGradient
import com.varabyte.kobweb.compose.ui.graphics.Color
import org.jetbrains.compose.web.css.*

fun kotlinGradient() =
    radialGradient(
        shape = RadialGradient.Shape.Circle(RadialGradient.Extent.FarthestSide),
        position = CSSPosition(100.percent, 0.percent),
    ) {
        add(Color.rgb(0xe44857))
        add(Color.rgb(0xc711e1), 50.45.percent)
        add(Color.rgb(0x7f52ff), 100.percent)
    }
