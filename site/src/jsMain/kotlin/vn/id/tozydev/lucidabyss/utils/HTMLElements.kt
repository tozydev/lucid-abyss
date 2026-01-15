package vn.id.tozydev.lucidabyss.utils

import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLHeadingElement
import org.w3c.dom.asList

fun HTMLElement.getHeadings(
    minHeaderLevel: Int = 2,
    maxHeaderLevel: Int = 2,
): List<HTMLHeadingElement> {
    require(minHeaderLevel in 1..6) { "minHeaderLevel must be in range 1..6, got $minHeaderLevel" }
    require(maxHeaderLevel in 1..6) { "maxHeaderLevel must be in range 1..6, got $maxHeaderLevel" }
    require(maxHeaderLevel >= minHeaderLevel) { "maxHeaderLevel must be >= minHeaderLevel, got $minHeaderLevel > $maxHeaderLevel" }

    return this
        .querySelectorAll((minHeaderLevel..maxHeaderLevel).joinToString { "h$it" })
        .asList()
        .unsafeCast<List<HTMLHeadingElement>>()
}
