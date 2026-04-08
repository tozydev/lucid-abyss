package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.code.CodeBlock
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.strings.title
import vn.id.tozydev.lucidabyss.utils.SiteRoutes
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun HomeHero(modifier: Modifier = Modifier) {
    Section(
        Modifier.tw("grid grid-cols-1 lg:grid-cols-12 gap-6 md:gap-8 items-stretch").then(modifier).toAttrs(),
    ) {
        Greeting(Modifier.tw("lg:col-span-5"))

        ShortIntro(Modifier.tw("lg:col-span-7"))
    }
}

@Composable
private fun ShortIntro(modifier: Modifier = Modifier) {
    CodeBlock(
        // language=kotlin
        code =
            """
            val tozydev = developer {
                about {
                    name = "Thanh Tân"
                    username = "tozydev"
                    role = Kotlin_Developer
                }
                technicalSkills {
                    languages = setOf("Kotlin", "Java", "TypeScript")
                    frameworks = setOf("Ktor", "Spring Boot", "Kobweb")
                }
                tools {
                    ide = setOf("IntelliJ IDEA")
                    codeEditor = setOf("VS Code")
                    ai = setOf("Gemini", "GitHub Copilot")
                }
            }
            """.trimIndent(),
        lang = "kotlin",
        modifier = Modifier.tw("my-0! hero-code-block").then(modifier),
    )
}

@Composable
private fun Greeting(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw(
                "bg-surface-container-lowest p-6 md:p-8 lg:p-12 rounded-xl shadow-soft flex flex-col justify-center space-y-4 md:space-y-6",
            ).then(modifier)
            .toAttrs(),
    ) {
        H1(
            {
                tw("text-3xl md:text-4xl font-headline font-extrabold text-secondary leading-tight")
            },
        ) {
            Strings.section.hero.title { first, second ->
                Text(first)
                Br()
                Span({ tw("text-primary text-4xl md:text-5xl lg:text-6xl") }) {
                    Text(second)
                }
            }
        }
        P(
            {
                tw("text-lg text-on-surface-variant leading-relaxed max-w-md")
            },
        ) {
            Text(Strings.section.hero.description)
        }

        GreetingActions()
    }
}

@Composable
private fun GreetingActions() {
    Div(
        {
            tw("flex flex-col sm:flex-row flex-wrap items-center gap-3 sm:gap-4 pt-2 md:pt-4")
        },
    ) {
        Anchor(
            href = SiteRoutes.about,
            attrs = {
                tw(
                    "bg-primary text-on-primary w-full sm:w-auto text-center px-6 md:px-8 py-3 md:py-4 rounded-md font-headline font-bold shadow-lg transition-all scale-100 active:scale-95 hover:bg-primary-container hover:text-on-primary-container",
                )
            },
        ) {
            Text(Strings.section.hero.actions.learnMore)
        }
        Anchor(
            href = SiteRoutes.blog,
            attrs = {
                tw(
                    "bg-surface-container-high text-on-surface w-full sm:w-auto text-center px-6 md:px-8 py-3 md:py-4 rounded-md font-headline font-bold hover:bg-surface-container-highest transition-colors",
                )
            },
        ) {
            Text(Strings.section.hero.actions.viewPosts)
        }
    }
}
