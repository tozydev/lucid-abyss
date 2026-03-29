package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun HeroCodeSnippet(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw("lg:col-span-7 bg-surface-container-highest rounded-2xl p-1 shadow-[0_20px_40px_rgba(42,40,37,0.06)] flex")
            .then(modifier)
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
                    HeroCodeLine(1, "tertiary", "class", "secondary", " Application ", null, "{")
                    HeroCodeLine(2, "tertiary", "    private var", null, " status = ", "error", "\"initializing\"")
                    HeroCodeLine(3)
                    HeroCodeLine(4, "secondary", "    init ", null, "{")
                    HeroCodeLine(5, null, "        println(", "error", "\"Khoi dong he thong...\"", null, ")")
                    HeroCodeLine(6, null, "    ", null, "}")
                    HeroCodeLine(7)
                    HeroCodeLine(8, "tertiary", "    fun ", "secondary", "deployArchitecture", null, "(): Structure {")
                    HeroCodeLine(9, "on-surface-variant", "        // Khoi tao nen tang kien truc sach")
                    HeroCodeLine(
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
                    HeroCodeLine(11, null, "    ", null, "}")
                    HeroCodeLine(12, null, "}")
                }
            }
        }
    }
}

@Composable
private fun HeroCodeLine(
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
                Text(color)
            }
            i += 2
        }
    }
}
