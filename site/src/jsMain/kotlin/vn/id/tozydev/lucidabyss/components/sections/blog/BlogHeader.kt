package vn.id.tozydev.lucidabyss.components.sections.blog

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun BlogHeader(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Header(
        Modifier
            .tw(
                "mb-8 bg-surface-container-lowest rounded-2xl p-6 md:p-8 lg:p-12 shadow-soft border-none",
            ).then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("max-w-3xl") }) {
            H1({ tw("font-headline text-3xl md:text-4xl lg:text-5xl font-extrabold tracking-tight text-primary mb-6") }) {
                Text(title)
            }
            P({ tw("text-on-surface-variant text-lg leading-relaxed") }) {
                Text(description)
            }
        }
    }
}
