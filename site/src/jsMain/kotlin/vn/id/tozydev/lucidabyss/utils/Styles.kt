package vn.id.tozydev.lucidabyss.utils

import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.css.setVariable
import org.jetbrains.compose.web.css.*

fun rgb(
    variable: StyleVariable<CSSColorValue, CSSColorValue>,
    alpha: Float = 1.0f,
): CSSColorValue = "rgb(from ${variable.value()} r g b / $alpha)".unsafeCast<CSSColorValue>()

context(scope: StyleScope)
infix fun <T : StylePropertyValue> StyleVariable.PropertyValue<T>.set(value: T) {
    scope.setVariable(this, value)
}
