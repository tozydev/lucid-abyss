package vn.id.tozydev.lucidabyss.components.sections.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutExperience(modifier: Modifier = Modifier) {
    Section(Modifier.tw("mb-16 max-w-170 mx-auto").then(modifier).toAttrs()) {
        Header {
            H2(Modifier.tw("font-headline text-xl font-bold text-on-surface mb-8").toAttrs()) {
                Text(Strings.pages.about.experience.title)
            }
        }

        Div(Modifier.tw("space-y-8").toAttrs()) {
            Strings.profile.experience.forEach { experience ->
                key(experience.title) {
                    AboutExperienceItem(
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
private fun AboutExperienceItem(
    title: String,
    period: String,
    company: String,
    description: String,
    dotColor: String,
) {
    Div(Modifier.tw("relative pl-6 border-l-2 border-outline-variant/30").toAttrs()) {
        Div(
            Modifier
                .tw("absolute w-3 h-3 $dotColor rounded-full -left-1.75 top-1.5 ring-4 ring-surface-container-lowest")
                .toAttrs(),
        )
        Div(Modifier.tw("flex flex-col md:flex-row md:items-baseline md:justify-between mb-2").toAttrs()) {
            H3(Modifier.tw("font-headline text-lg font-bold text-on-surface").toAttrs()) {
                Text(title)
            }
            Span(
                Modifier
                    .tw("font-label text-sm font-medium text-on-surface-variant uppercase tracking-wider mt-1 md:mt-0")
                    .toAttrs(),
            ) {
                Text(period)
            }
        }
        P(Modifier.tw("font-body text-base font-medium text-primary mb-2").toAttrs()) {
            Text(company)
        }
        P(Modifier.tw("font-body text-[15px] text-on-surface-variant leading-relaxed").toAttrs()) {
            Text(description)
        }
    }
}
