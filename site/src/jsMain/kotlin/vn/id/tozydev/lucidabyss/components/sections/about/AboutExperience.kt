package vn.id.tozydev.lucidabyss.components.sections.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutExperience(modifier: Modifier = Modifier) {
    AboutSection(
        title = Strings.pages.about.experience.title,
        modifier = modifier,
    ) {
        Div({ tw("space-y-8") }) {
            Strings.profile.experience.forEach { experience ->
                key(experience.title) {
                    ExperienceItem(
                        title = experience.title,
                        period = experience.period,
                        company = experience.company,
                        description = experience.description,
                        dotColor = experience.dotColor,
                    )
                }
            }
        }
    }
}

@Composable
private fun ExperienceItem(
    title: String,
    period: String,
    company: String,
    description: String,
    dotColor: String,
) {
    Div({ tw("relative pl-6 border-l-2 border-outline-variant/30") }) {
        Div({ tw("absolute w-3 h-3 $dotColor rounded-full -left-1.75 top-1.5 ring-4 ring-surface-container-lowest") })
        Div(Modifier.tw("flex flex-col md:flex-row md:items-baseline md:justify-between mb-2").toAttrs()) {
            H3({ tw("font-headline text-lg font-bold text-on-surface") }) {
                Text(title)
            }
            Span(
                { tw("font-label text-sm font-medium text-on-surface-variant uppercase tracking-wider mt-1 md:mt-0") },
            ) {
                Text(period)
            }
        }
        P({ tw("font-body text-base font-medium text-primary mb-2") }) {
            Text(company)
        }
        P({ tw("font-body text-on-surface-variant leading-relaxed") }) {
            Text(description)
        }
    }
}
