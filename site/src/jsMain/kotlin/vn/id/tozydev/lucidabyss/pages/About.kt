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
import vn.id.tozydev.lucidabyss.utils.pagefind.pagefindBody
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
            tw("max-w-200 mx-auto bg-surface-container-lowest rounded-xl overflow-hidden shadow-soft")
        },
    ) {
        AboutHero()
        Article(
            {
                tw("pt-16 md:pt-20 pb-12 md:pb-16 px-6 md:px-20")
                pagefindBody()
            },
        ) {
            val sectionModifier = Modifier.tw("mb-16")

            AboutIntro(sectionModifier)

            AboutSkills(sectionModifier)
            AboutExperience(sectionModifier)
            AboutProjects(sectionModifier)

            Hr { tw("border-outline-variant mb-16 max-w-170 mx-auto") }

            AboutContact()
        }
    }
}
