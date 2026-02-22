package vn.id.tozydev.lucidabyss.components.about

import Res
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaEnvelope
import com.varabyte.kobweb.silk.components.icons.fa.FaPaperPlane
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.core.Links
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutContact(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw("card card-border bg-base-100 hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] transition-all duration-300")
            .then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body items-center text-center") }) {
            H2({ tw("card-title text-base mb-2") }) {
                FaEnvelope()
                Text(Res.string.section_about_contact_title)
            }
            P({ tw("text-base-content/80 mb-4") }) {
                Text(Res.string.section_about_contact_content)
            }
            A(
                href = Links.EMAIL_URL,
                attrs = { tw("btn btn-primary btn-wide") }
            ) {
                FaPaperPlane()
                Text(Res.string.section_about_contact_button)
            }
        }
    }
}
