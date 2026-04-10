package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.layouts.PAGE_LAYOUT_FNQ
import vn.id.tozydev.lucidabyss.components.layouts.PageProperties
import vn.id.tozydev.lucidabyss.components.sections.about.AboutContact
import vn.id.tozydev.lucidabyss.components.sections.about.AboutExperience
import vn.id.tozydev.lucidabyss.components.sections.about.AboutHero
import vn.id.tozydev.lucidabyss.components.sections.about.AboutIntro
import vn.id.tozydev.lucidabyss.components.sections.about.AboutProjects
import vn.id.tozydev.lucidabyss.components.sections.about.AboutSkills
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@InitRoute
fun initAboutPage(ctx: InitRouteContext) {
    ctx.data.add(
        PageProperties(
            title = Strings.pages.about.title,
            description = Strings.pages.about.description,
        ),
    )
}

@Composable
@Page
@Layout(PAGE_LAYOUT_FNQ)
fun AboutPage() {
    Div(
        {
            tw("max-w-200 mx-auto bg-surface-container-lowest rounded-xl overflow-hidden")
            tw("shadow-[0_20px_40px_rgba(42,40,37,0.06)]")
        },
    ) {
        AboutHero()
        Article({ tw("pt-16 md:pt-20 pb-12 md:pb-16 px-6 md:px-20") }) {
            AboutIntro(Modifier.tw("mb-16"))

            AboutSkills()
            AboutExperience()
            AboutProjects()

            Hr { tw("border-outline-variant/30 mb-16 max-w-170 mx-auto") }

            AboutContact()
        }
    }
}
