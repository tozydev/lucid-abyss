package vn.id.tozydev.lucidabyss.components.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutSkillGroup(
    title: String,
    icon: @Composable () -> Unit,
    skills: List<String>,
    modifier: Modifier = Modifier,
) {
    Div(
        Modifier
            .tw("card card-border bg-base-100 hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] transition-all duration-300")
            .then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body") }) {
            H2({ tw("card-title text-base") }) {
                icon()
                Text(title)
            }
            Div({ tw("flex flex-wrap gap-2") }) {
                skills.forEach { skill ->
                    Div({ tw("badge badge-outline badge-lg") }) {
                        Text(skill)
                    }
                }
            }
        }
    }
}
