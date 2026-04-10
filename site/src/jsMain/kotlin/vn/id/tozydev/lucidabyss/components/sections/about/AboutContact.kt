package vn.id.tozydev.lucidabyss.components.sections.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutContact(modifier: Modifier = Modifier) {
    Section(Modifier.tw("space-y-6 max-w-170 mx-auto").then(modifier).toAttrs()) {
        Header {
            H2(Modifier.tw("font-headline text-xl font-bold text-on-surface").toAttrs()) {
                Text(Strings.pages.about.contact.title)
            }
        }
        P(Modifier.tw("text-on-surface-variant leading-relaxed").toAttrs()) {
            Text(Strings.pages.about.contact.description)
        }
        Div {
            Anchor(
                href = "mailto:${Strings.profile.contact.email}",
                attrs =
                    Modifier
                        .tw(
                            "inline-block bg-primary text-on-primary px-8 py-3.5 rounded-xl font-headline font-bold text-base shadow-md hover:bg-primary-container hover:text-on-primary-container transition-all scale-100 active:scale-95",
                        ).toAttrs(),
            ) {
                Text(Strings.profile.contact.email)
            }
        }
    }
}
