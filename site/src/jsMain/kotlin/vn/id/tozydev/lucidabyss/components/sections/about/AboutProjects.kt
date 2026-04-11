package vn.id.tozydev.lucidabyss.components.sections.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.OpenInNewIcon
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutProjects(modifier: Modifier = Modifier) {
    AboutSection(
        title = Strings.pages.about.projects.title,
        modifier = modifier,
    ) {
        Div({ tw("space-y-6") }) {
            Strings.profile.projects.forEach { project ->
                key(project.title) {
                    AboutProjectItem(
                        title = project.title,
                        description = project.description,
                        tags = project.tags,
                        href = project.link,
                    )
                }
            }
        }
    }
}

@Composable
private fun AboutProjectItem(
    title: String,
    description: String,
    tags: List<String>,
    href: String,
) {
    Div(
        {
            tw(
                "group block p-6 bg-surface-container-low hover:bg-surface-container rounded-2xl border border-outline/30 transition-all duration-300",
            )
        },
    ) {
        Div({ tw("flex justify-between items-start mb-3") }) {
            H3(
                {
                    tw("font-headline text-lg font-bold text-on-surface group-hover:text-primary transition-colors")
                },
            ) {
                Text(title)
            }
            Anchor(
                href = href,
                attrs = { tw("text-on-surface-variant hover:text-primary p-1") },
            ) {
                OpenInNewIcon(Modifier.tw("text-sm"))
            }
        }
        P({ tw("font-body text-on-surface-variant leading-relaxed mb-4") }) {
            Text(description)
        }
        Div({ tw("flex flex-wrap gap-2") }) {
            tags.forEach { tag ->
                Span(
                    { tw("font-label text-xs font-bold text-on-surface-variant bg-surface-variant px-3 py-1 rounded-full") },
                ) {
                    Text(tag)
                }
            }
        }
    }
}
