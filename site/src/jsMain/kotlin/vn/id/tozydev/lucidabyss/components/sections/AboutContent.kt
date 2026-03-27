package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun AboutContent(modifier: Modifier = Modifier) {
    Section(Modifier.then(modifier).toAttrs()) {
        Header(Modifier.tw("text-center mb-12").toAttrs()) {
            H1(
                Modifier.tw("text-3xl md:text-5xl font-black font-headline text-primary tracking-tight mb-2").toAttrs(),
            ) {
                Text(Strings.page.about.name)
            }
            P(Modifier.tw("font-label text-secondary uppercase tracking-[0.2em] text-sm font-bold").toAttrs()) {
                Text(Strings.page.about.role)
            }
        }

        // Intro Text
        Div(
            Modifier
                .tw("max-w-170 mx-auto space-y-8 text-on-surface-variant leading-relaxed text-[17px] mb-16")
                .toAttrs(),
        ) {
            P {
                Text(Strings.page.about.introP11)
                Span({ tw("text-primary font-bold") }) {
                    Text(Strings.page.about.introP1Highlight)
                }
                Text(Strings.page.about.introP12)
            }

            Div(
                Modifier
                    .tw(
                        "py-4 px-8 bg-surface-container rounded-xl italic font-medium text-primary-container text-center border-l-4 border-primary",
                    ).toAttrs(),
            ) {
                Text("\"${Strings.page.about.quote}\"")
            }

            P {
                Text(Strings.page.about.introP21)
                Span({ tw("text-primary font-bold") }) {
                    Text(Strings.page.about.introP2Highlight)
                }
                Text(Strings.page.about.introP22)
                Span({ tw("text-primary font-bold") }) {
                    Text(Strings.page.about.introP2Highlight2)
                }
                Text(Strings.page.about.introP23)
            }

            P {
                Text(Strings.page.about.introP3)
            }
        }
    }
}
