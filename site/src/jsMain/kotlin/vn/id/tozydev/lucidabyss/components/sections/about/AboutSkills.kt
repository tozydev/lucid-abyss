package vn.id.tozydev.lucidabyss.components.sections.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutSkills(modifier: Modifier = Modifier) {
    AboutSection(
        title = Strings.pages.about.skills.title,
        modifier = modifier,
    ) {
        Div({ tw("flex flex-wrap gap-3") }) {
            Strings.profile.skills.forEach { skill ->
                AboutSkillTag(skill)
            }
        }
    }
}

@Composable
private fun AboutSkillTag(name: String) {
    Span({ tw("px-5 py-2.5 font-label text-sm font-bold rounded-xl bg-surface-container-low text-on-surface-variant") }) {
        Text(name)
    }
}
