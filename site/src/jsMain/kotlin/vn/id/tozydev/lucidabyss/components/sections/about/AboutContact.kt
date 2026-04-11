package vn.id.tozydev.lucidabyss.components.sections.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutContact(modifier: Modifier = Modifier) {
    AboutSection(
        title = Strings.pages.about.contact.title,
        description = Strings.pages.about.contact.description,
        modifier = modifier,
    ) {
        Div {
            Anchor(
                href = "mailto:${Strings.profile.contact.email}",
                attrs = {
                    tw("btn btn-primary btn-lg inline-block font-label font-bold shadow-md")
                },
            ) {
                Text(Strings.profile.contact.email)
            }
        }
    }
}
