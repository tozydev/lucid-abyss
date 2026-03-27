package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    linkText: String? = null,
    linkHref: String? = null,
) {
    Div(
        Modifier
            .tw("flex flex-col md:flex-row justify-between items-start md:items-end gap-4 mb-8 md:mb-12")
            .then(modifier)
            .toAttrs(),
    ) {
        Div {
            H2({ tw("text-2xl md:text-3xl font-headline font-extrabold text-primary mb-2") }) {
                Text(title)
            }
            description?.let {
                P({ tw("text-on-surface-variant font-body") }) {
                    Text(it)
                }
            }
        }
        if (linkText != null && linkHref != null) {
            Anchor(
                href = linkHref,
                attrs = Modifier.tw("text-secondary font-headline font-bold flex items-center gap-2 group").toAttrs(),
            ) {
                Text(linkText)
                MaterialSymbol(
                    "arrow_forward",
                    Modifier.tw("group-hover:translate-x-1 transition-transform"),
                )
            }
        }
    }
}
