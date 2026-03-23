package vn.id.tozydev.lucidabyss.styles

import com.varabyte.kobweb.compose.css.CSSLengthNumericValue
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.css.functions.calc
import org.jetbrains.compose.web.css.*

val Spacing by StyleVariable<CSSLengthNumericValue>(0.25.cssRem)

val Number.spacing: CSSLengthNumericValue
    get() = calc { Spacing.value() * this@spacing }
