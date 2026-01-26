package vn.id.tozydev.lucidabyss.utils

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import org.jetbrains.compose.web.attributes.AttrsScope
import org.w3c.dom.Element

/** Alias for `Modifier.classNames` with tailwind classes IDE completion support. */
fun Modifier.tw(classes: String): Modifier =
    attrsModifier {
        tw(classes)
    }

/** Alias for [AttrsScope.classes] with tailwind classes IDE completion support. */
fun <T : Element> AttrsScope<T>.tw(classes: String) {
    classes(classes.split(' '))
}
