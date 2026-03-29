package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.layouts.PAGE_LAYOUT_FNQ
import vn.id.tozydev.lucidabyss.components.layouts.PageProperties
import vn.id.tozydev.lucidabyss.components.sections.AboutContact
import vn.id.tozydev.lucidabyss.components.sections.AboutContent
import vn.id.tozydev.lucidabyss.components.sections.AboutExperience
import vn.id.tozydev.lucidabyss.components.sections.AboutHero
import vn.id.tozydev.lucidabyss.components.sections.AboutProjects
import vn.id.tozydev.lucidabyss.components.sections.AboutSkills
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.utils.tw

@InitRoute
fun initAboutPage(ctx: InitRouteContext) {
    ctx.data.add(
        PageProperties(
            title = Strings.page.about.title,
            description = Strings.page.about.description,
        ),
    )
}

@Page
@Layout(PAGE_LAYOUT_FNQ)
@Composable
fun AboutPage() {
    Div(
        Modifier
            .tw(
                "max-w-[800px] mx-auto bg-surface-container-lowest rounded-[2rem] overflow-hidden shadow-[0_20px_40px_rgba(42,40,37,0.06)]",
            ).toAttrs(),
    ) {
        AboutHero()
        Section(Modifier.tw("pt-16 md:pt-20 pb-12 md:pb-16 px-6 md:px-20").toAttrs()) {
            AboutContent()
            AboutSkills()
            AboutExperience()
            AboutProjects()

            Hr(Modifier.tw("border-outline-variant/30 mb-16 max-w-170 mx-auto").toAttrs())

            AboutContact()
        }
    }
}
