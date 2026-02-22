package vn.id.tozydev.lucidabyss.components.about

import Res
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaBriefcase
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutExperience(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw("card card-border bg-base-100 hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] transition-all duration-300")
            .then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body") }) {
            H2({ tw("card-title text-base mb-4") }) {
                FaBriefcase()
                Text(Res.string.section_about_experience_title)
            }
            Div({ tw("flex flex-col gap-6") }) {
                // Mock Item 1
                ExperienceItem(
                    role = Res.string.section_about_experience_item_role,
                    company = Res.string.section_about_experience_item_company,
                    date = Res.string.section_about_experience_item_date,
                    description = Res.string.section_about_experience_item_desc
                )
                 // Mock Item 2 (Duplicate for visual fullness)
                ExperienceItem(
                    role = "Senior Developer",
                    company = "Another Corp",
                    date = "2018 - 2020",
                    description = "Led the frontend team and improved site performance."
                )
            }
        }
    }
}

@Composable
private fun ExperienceItem(
    role: String,
    company: String,
    date: String,
    description: String
) {
    Div({ tw("flex flex-col gap-1 border-l-2 border-base-300 pl-4 relative") }) {
        Div({ tw("absolute -left-[5px] top-2 w-2 h-2 rounded-full bg-primary") })
        Div({ tw("flex justify-between items-baseline flex-wrap") }) {
            H3({ tw("font-bold text-lg leading-tight") }) {
                Text(role)
            }
            Span({ tw("text-xs font-mono opacity-60 bg-base-200 px-2 py-1 rounded") }) {
                Text(date)
            }
        }
        Div({ tw("text-primary font-medium text-sm") }) {
            Text(company)
        }
        P({ tw("text-sm opacity-80 mt-1") }) {
            Text(description)
        }
    }
}
