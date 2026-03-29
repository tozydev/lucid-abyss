package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.HeroCodeSnippet
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.strings.title
import vn.id.tozydev.lucidabyss.utils.SiteRoutes
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun HomeHero(modifier: Modifier = Modifier) {
    Section(
        Modifier.tw("grid grid-cols-1 lg:grid-cols-12 gap-6 md:gap-8 items-stretch mb-24").then(modifier).toAttrs(),
    ) {
        // Text Island
        Div(
            Modifier
                .tw(
                    "lg:col-span-5 bg-surface-container-lowest p-6 md:p-8 lg:p-12 rounded-2xl shadow-[0_20px_40px_rgba(42,40,37,0.06)] flex flex-col justify-center space-y-4 md:space-y-6",
                ).toAttrs(),
        ) {
            H1(
                Modifier
                    .tw("text-4xl md:text-5xl lg:text-6xl font-headline font-extrabold text-primary leading-tight")
                    .toAttrs(),
            ) {
                Strings.section.hero.title { first, second ->
                    Text(first)
                    Span({ tw("text-secondary") }) {
                        Text(second)
                    }
                }
            }
            P(Modifier.tw("text-lg text-on-surface-variant leading-relaxed max-w-md").toAttrs()) {
                Text(Strings.section.hero.description)
            }

            Div(Modifier.tw("flex flex-col sm:flex-row flex-wrap items-center gap-3 sm:gap-4 pt-2 md:pt-4").toAttrs()) {
                Anchor(
                    href = SiteRoutes.about,
                    attrs =
                        Modifier
                            .tw(
                                "bg-linear-to-br from-primary to-primary-container text-on-primary w-full sm:w-auto text-center px-6 md:px-8 py-3 md:py-4 rounded-xl font-headline font-bold shadow-lg hover:opacity-90 transition-opacity",
                            ).toAttrs(),
                ) {
                    Text(Strings.section.hero.viewProjects)
                }
                Anchor(
                    href = "mailto:hello@devisland.com",
                    attrs =
                        Modifier
                            .tw(
                                "bg-surface-container-high text-primary w-full sm:w-auto text-center px-6 md:px-8 py-3 md:py-4 rounded-xl font-headline font-bold hover:bg-surface-container-highest transition-colors",
                            ).toAttrs(),
                ) {
                    Text(Strings.section.hero.contact)
                }
            }
        }

        HeroCodeSnippet()
    }
}
