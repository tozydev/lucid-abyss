package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutSkills(modifier: Modifier = Modifier) {
    Section(Modifier.tw("mb-16 max-w-170 mx-auto").then(modifier).toAttrs()) {
        Header {
            H2(Modifier.tw("font-headline text-xl font-bold text-on-surface mb-8").toAttrs()) {
                Text(Strings.page.about.skillsTitle)
            }
        }
        Div(Modifier.tw("flex flex-wrap gap-3").toAttrs()) {
            Strings.page.about.skills.forEach { skill ->
                SkillTag(skill)
            }
        }
    }
}

@Composable
private fun SkillTag(name: String) {
    Span(
        Modifier
            .tw("px-5 py-2.5 font-label text-[13px] font-bold rounded-xl bg-surface-container-low text-on-surface-variant")
            .toAttrs(),
    ) {
        Text(name)
    }
}
