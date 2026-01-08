package vn.id.tozydev.lucidabyss.utils

import com.varabyte.kobweb.compose.css.CSSLengthNumericValue
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*

fun Modifier.inset(value: CSSLengthNumericValue) = top(value).right(value).bottom(value).left(value)

/**
 * Alias for [classNames] that applies classes to this modifier.
 * This is a convenience function to get Tailwind CSS classes completion in IDEs.
 */
fun Modifier.tw(vararg classes: String): Modifier =
    classNames(
        classes.flatMap { it.split(' ') },
    )
