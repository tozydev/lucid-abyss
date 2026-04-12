package vn.id.tozydev.lucidabyss.components.sections.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.sections.AboutStory
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutIntro(modifier: Modifier = Modifier) {
    Section(modifier.toAttrs()) {
        IntroHeader()

        Div({ tw("prose md:prose-lg prose-blog max-w-none wrap-break-word") }) {
            AboutStory()
        }
    }
}

@Composable
private fun IntroHeader() {
    Header({ tw("text-center mb-12") }) {
        H1(
            { tw("text-3xl md:text-5xl font-black font-headline text-primary tracking-tight mb-2") },
        ) {
            Text(Strings.profile.name)
        }
        P({ tw("font-label text-secondary uppercase tracking-[0.2em] text-sm font-bold") }) {
            Text(Strings.profile.role)
        }
    }
}
