package vn.id.tozydev.lucidabyss.components.sections.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutSection(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    content: @Composable () -> Unit,
) {
    Section(modifier.toAttrs()) {
        Header {
            H2({ tw("font-headline text-xl md:text-2xl font-bold text-secondary mb-8") }) {
                Text(title)
            }
            if (description != null) {
                P({ tw("text-on-surface-variant leading-relaxed mb-6") }) {
                    Text(description)
                }
            }
        }

        content()
    }
}
