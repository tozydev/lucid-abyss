package vn.id.tozydev.lucidabyss.components.sections.home

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.CheckIcon
import vn.id.tozydev.lucidabyss.components.widgets.PersonIcon
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
    Div(
        Modifier
            .tw("flex flex-col rounded-xl overflow-hidden shadow-soft bg-surface border border-outline/30")
            .then(modifier)
            .toAttrs(),
    ) {
        Div(
            { tw("flex items-center justify-between bg-surface-container-low border-b border-outline/30") },
        ) {
            Div(Modifier.tw("flex").toAttrs()) {
                Div({ tw("flex items-center gap-2 px-4 py-3 bg-surface border-r border-outline/30 relative") }) {
                    PersonIcon(Modifier.tw("w-4 h-4 text-primary"))
                    Span({ tw("text-xs font-mono text-on-surface") }) { Text("Profile.kt") }
                    Div({ tw("absolute bottom-0 left-0 right-0 h-0.5 bg-primary") })
                }
            }
            Div({ tw("flex gap-2 pr-4") }) {
                Div({ tw("w-2.5 h-2.5 rounded-full bg-outline-variant") })
                Div({ tw("w-2.5 h-2.5 rounded-full bg-outline-variant") })
                Div({ tw("w-2.5 h-2.5 rounded-full bg-outline-variant") })
            }
        }
        Div({ tw("flex-1 overflow-hidden relative") }) {
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
                modifier = Modifier.tw("my-0! hero-code-block rounded-none! border-none! shadow-none!"),
            )
        }
        Div(
            { tw("px-4 py-1.5 bg-primary text-on-primary text-[10px] font-mono flex justify-between items-center") },
        ) {
            Div({ tw("flex items-center gap-2") }) {
                CheckIcon(Modifier.tw("w-3.5 h-3.5"))
                Span { Text("SYSTEM: OPTIMIZED") }
            }
            Span { Text("main* ᛡ utf-8") }
        }
    }
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
            Strings.pages.home.hero.title { first, second ->
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
            Text(Strings.pages.home.hero.description)
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
                tw("btn btn-primary btn-lg w-full sm:w-auto font-semibold shadow-lg")
            },
        ) {
            Text(Strings.commons.actions.learnMore)
        }
        Anchor(
            href = SiteRoutes.blog,
            attrs = {
                tw("btn btn-neutral btn-lg w-full sm:w-auto font-semibold")
            },
        ) {
            Text(Strings.commons.actions.viewPosts)
        }
    }
}
