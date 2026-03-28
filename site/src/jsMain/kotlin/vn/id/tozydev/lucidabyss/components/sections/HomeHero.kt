package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import org.jetbrains.compose.web.dom.*
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
                val ctx = rememberPageContext()
                A(
                    href = SiteRoutes.about,
                    attrs =
                        Modifier
                            .tw(
                                "bg-linear-to-br from-primary to-primary-container text-on-primary w-full sm:w-auto text-center px-6 md:px-8 py-3 md:py-4 rounded-xl font-headline font-bold shadow-lg hover:opacity-90 transition-opacity",
                            ).toAttrs {
                                onClick {
                                    ctx.router.navigateTo(SiteRoutes.about)
                                }
                            },
                ) {
                    Text(Strings.section.hero.viewProjects)
                }
                A(
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

        // Code Snippet Island (Kotlin Edition)
        Div(
            Modifier
                .tw("lg:col-span-7 bg-surface-container-highest rounded-2xl p-1 shadow-[0_20px_40px_rgba(42,40,37,0.06)] flex")
                .toAttrs(),
        ) {
            Div(Modifier.tw("bg-inverse-surface rounded-xl overflow-hidden w-full flex flex-col").toAttrs()) {
                Div(Modifier.tw("flex items-center justify-between px-4 py-3 bg-surface-container-highest").toAttrs()) {
                    Div(Modifier.tw("flex gap-2").toAttrs()) {
                        Div({ tw("w-3 h-3 rounded-full bg-red-500/20") })
                        Div({ tw("w-3 h-3 rounded-full bg-amber-500/20") })
                        Div({ tw("w-3 h-3 rounded-full bg-emerald-500/20") })
                    }
                    Span(Modifier.tw("text-xs font-mono text-on-surface-variant").toAttrs()) {
                        Text("Application.kt")
                    }
                }
                Div(
                    Modifier
                        .tw("p-4 md:p-8 font-mono text-xs md:text-sm leading-relaxed overflow-x-auto flex-1")
                        .toAttrs(),
                ) {
                    Pre(Modifier.tw("text-primary").toAttrs()) {
                        CodeLine(1, "tertiary", "class", "secondary", " Application ", null, "{")
                        CodeLine(2, "tertiary", "    private var", null, " status = ", "error", "\"initializing\"")
                        CodeLine(3)
                        CodeLine(4, "secondary", "    init ", null, "{")
                        CodeLine(5, null, "        println(", "error", "\"Khởi động hệ thống...\"", null, ")")
                        CodeLine(6, null, "    ", null, "}")
                        CodeLine(7)
                        CodeLine(8, "tertiary", "    fun ", "secondary", "deployArchitecture", null, "(): Structure {")
                        CodeLine(9, "on-surface-variant", "        // Khởi tạo nền tảng kiến trúc sạch")
                        CodeLine(
                            10,
                            "tertiary",
                            "        return ",
                            null,
                            "Structure(scalable = ",
                            "tertiary",
                            "true",
                            null,
                            ")",
                        )
                        CodeLine(11, null, "    ", null, "}")
                        CodeLine(12, null, "}")
                    }
                }
            }
        }
    }
}

@Composable
private fun CodeLine(
    number: Int,
    vararg parts: Any?,
) {
    Div {
        Span(Modifier.tw("text-on-surface-variant").toAttrs()) {
            Text(number.toString().padEnd(2))
        }
        Text(" ")
        var i = 0
        while (i < parts.size) {
            val color = parts[i] as? String
            val content = if (i + 1 < parts.size) parts[i + 1] as? String else null
            if (content != null) {
                if (color != null) {
                    val textColorClass =
                        when (color) {
                            "tertiary" -> "text-tertiary"
                            "secondary" -> "text-secondary"
                            "error" -> "text-error"
                            "on-surface-variant" -> "text-on-surface-variant"
                            else -> "text-primary"
                        }
                    Span(Modifier.tw(textColorClass).toAttrs()) {
                        Text(content)
                    }
                } else {
                    Text(content)
                }
            } else if (color != null && i + 1 >= parts.size) {
                // If only one part is left, it's content with no color
                Text(color)
            }
            i += 2
        }
    }
}
