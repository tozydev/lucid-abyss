package vn.id.tozydev.lucidabyss.utils

import com.varabyte.kobweb.compose.css.CSSLengthNumericValue
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*

fun Modifier.inset(value: CSSLengthNumericValue) = top(value).right(value).bottom(value).left(value)
