package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.MaterialSymbol
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutProjects(modifier: Modifier = Modifier) {
    Section(Modifier.tw("mb-16 max-w-170 mx-auto").then(modifier).toAttrs()) {
        H2(Modifier.tw("font-headline text-xl font-bold text-on-surface mb-8").toAttrs()) {
            Text(Strings.page.about.projectsTitle)
        }

        Div(Modifier.tw("space-y-6").toAttrs()) {
            ProjectItem(
                title = Strings.page.about.projects.payflow.title,
                description = Strings.page.about.projects.payflow.description,
                tags = listOf("Kotlin", "Ktor", "Coroutines"),
                href = "#",
            )
            ProjectItem(
                title = Strings.page.about.projects.taskminder.title,
                description = Strings.page.about.projects.taskminder.description,
                tags = listOf("Jetpack Compose", "MVI", "Room"),
                href = "#",
            )
        }
    }
}

@Composable
private fun ProjectItem(
    title: String,
    description: String,
    tags: List<String>,
    href: String,
) {
    Div(
        Modifier
            .tw(
                "group block p-6 bg-surface-container-low hover:bg-surface-container rounded-2xl border border-outline-variant/30 transition-all duration-300",
            ).toAttrs(),
    ) {
        Div(Modifier.tw("flex justify-between items-start mb-3").toAttrs()) {
            H3(
                Modifier
                    .tw("font-headline text-lg font-bold text-on-surface group-hover:text-primary transition-colors")
                    .toAttrs(),
            ) {
                Text(title)
            }
            A(
                href = href,
                attrs = Modifier.tw("text-on-surface-variant hover:text-primary p-1").toAttrs(),
            ) {
                MaterialSymbol("open_in_new", Modifier.tw("text-[20px]"))
            }
        }
        P(Modifier.tw("font-body text-[15px] text-on-surface-variant leading-relaxed mb-4").toAttrs()) {
            Text(description)
        }
        Div(Modifier.tw("flex flex-wrap gap-2").toAttrs()) {
            tags.forEach { tag ->
                Span(
                    Modifier
                        .tw("font-label text-xs font-bold text-primary-container bg-primary/10 px-3 py-1 rounded-full")
                        .toAttrs(),
                ) {
                    Text(tag)
                }
            }
        }
    }
}
