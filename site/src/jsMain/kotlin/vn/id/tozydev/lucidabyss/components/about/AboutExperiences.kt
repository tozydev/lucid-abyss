package vn.id.tozydev.lucidabyss.components.about

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaBriefcase
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

data class Experience(
    val title: String,
    val company: String,
    val date: String,
    val description: String,
)

@Composable
fun AboutExperiences(modifier: Modifier = Modifier) {
    val experiences =
        listOf(
            Experience(
                Strings.widget.about.experiences.exp1.title,
                Strings.widget.about.experiences.exp1.company,
                Strings.widget.about.experiences.exp1.date,
                Strings.widget.about.experiences.exp1.description,
            ),
            Experience(
                Strings.widget.about.experiences.exp2.title,
                Strings.widget.about.experiences.exp2.company,
                Strings.widget.about.experiences.exp2.date,
                Strings.widget.about.experiences.exp2.description,
            ),
        )

    Div(
        Modifier
            .tw(
                "card card-border bg-base-100 card-lg hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] transition-all duration-300",
            ).then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body") }) {
            H2({ tw("card-title text-base mb-4") }) {
                FaBriefcase()
                Text(Strings.widget.about.experiences.title)
            }
            Ul({ tw("timeline timeline-vertical timeline-compact") }) {
                experiences.forEachIndexed { index, experience ->
                    Li {
                        if (index > 0) {
                            Hr()
                        }
                        Div({ tw("timeline-middle") }) {
                            Div({ tw("w-3 h-3 bg-primary rounded-full") })
                        }
                        Div({ tw("timeline-end timeline-box bg-transparent border-none shadow-none text-start pb-4") }) {
                            Span({ tw("text-xs font-mono text-base-content/50") }) { Text(experience.date) }
                            H3({ tw("text-sm font-bold mt-1") }) { Text(experience.title) }
                            H4({ tw("text-xs font-semibold text-primary mb-2") }) { Text(experience.company) }
                            P({ tw("text-sm text-base-content/80") }) { Text(experience.description) }
                        }
                        if (index < experiences.size - 1) {
                            Hr()
                        }
                    }
                }
            }
        }
    }
}
