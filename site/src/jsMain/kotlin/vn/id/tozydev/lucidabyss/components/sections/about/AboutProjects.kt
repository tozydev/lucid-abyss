package vn.id.tozydev.lucidabyss.components.sections.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.OpenInNewIcon
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutProjects(modifier: Modifier = Modifier) {
    Section(Modifier.tw("mb-16 max-w-170 mx-auto").then(modifier).toAttrs()) {
        Header {
            H2(Modifier.tw("font-headline text-xl font-bold text-on-surface mb-8").toAttrs()) {
                Text(Strings.pages.about.projects.title)
            }
        }

        Div(Modifier.tw("space-y-6").toAttrs()) {
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
            Anchor(
                href = href,
                attrs = Modifier.tw("text-on-surface-variant hover:text-primary p-1").toAttrs(),
            ) {
                OpenInNewIcon(Modifier.tw("text-xl"))
            }
        }
        P(Modifier.tw("font-body text-[15px] text-on-surface-variant leading-relaxed mb-4").toAttrs()) {
            Text(description)
        }
        Div(Modifier.tw("flex flex-wrap gap-2").toAttrs()) {
            tags.forEach { tag ->
                Span(
                    Modifier
                        .tw("font-label text-xs font-bold text-on-surface-variant bg-surface-variant px-3 py-1 rounded-full")
                        .toAttrs(),
                ) {
                    Text(tag)
                }
            }
        }
    }
}
