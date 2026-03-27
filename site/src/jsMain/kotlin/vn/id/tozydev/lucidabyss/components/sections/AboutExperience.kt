package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutExperience(modifier: Modifier = Modifier) {
    Section(Modifier.tw("mb-16 max-w-170 mx-auto").then(modifier).toAttrs()) {
        H2(Modifier.tw("font-headline text-xl font-bold text-on-surface mb-8").toAttrs()) {
            Text(Strings.page.about.experienceTitle)
        }

        Div(Modifier.tw("space-y-8").toAttrs()) {
            ExperienceItem(
                title = Strings.page.about.experience.senior.title,
                period = Strings.page.about.experience.senior.period,
                company = Strings.page.about.experience.senior.company,
                description = Strings.page.about.experience.senior.description,
                dotColor = "bg-primary",
            )
            ExperienceItem(
                title = Strings.page.about.experience.mid.title,
                period = Strings.page.about.experience.mid.period,
                company = Strings.page.about.experience.mid.company,
                description = Strings.page.about.experience.mid.description,
                dotColor = "bg-secondary",
            )
            ExperienceItem(
                title = Strings.page.about.experience.junior.title,
                period = Strings.page.about.experience.junior.period,
                company = Strings.page.about.experience.junior.company,
                description = Strings.page.about.experience.junior.description,
                dotColor = "bg-tertiary",
            )
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
