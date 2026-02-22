package vn.id.tozydev.lucidabyss.pages

import Res
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaCode
import com.varabyte.kobweb.silk.components.icons.fa.FaLayerGroup
import com.varabyte.kobweb.silk.components.icons.fa.FaCode
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.about.AboutContact
import vn.id.tozydev.lucidabyss.components.about.AboutExperience
import vn.id.tozydev.lucidabyss.components.about.AboutSkillGroup
import vn.id.tozydev.lucidabyss.components.about.AboutStory
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths
import vn.id.tozydev.lucidabyss.utils.strings
import vn.id.tozydev.lucidabyss.utils.tw

class AboutPage(
    language: SiteLanguage,
) : Page(language) {
    override val route = SitePaths.ABOUT_PATH
    override val properties =
        language.strings.let { strings ->
            Properties(
                title = strings.page_about_title!!,
                description = strings.page_about_description!!,
            )
        }

    @Composable
    override fun Content(ctx: PageContext) {
        Div({ tw("grid auto-rows-min grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-5") }) {
            AboutStory(
                Modifier.tw("col-span-1 md:col-span-2 lg:col-span-2 lg:row-span-2")
            )

            AboutExperience(
                Modifier.tw("col-span-1 md:col-span-2 lg:col-span-2 lg:row-span-2")
            )

            AboutSkillGroup(
                title = Res.string.section_about_skills_languages_title,
                icon = { FaCode() },
                skills = listOf("Kotlin", "Java", "JavaScript", "Python"),
                modifier = Modifier.tw("col-span-1")
            )

            AboutSkillGroup(
                title = Res.string.section_about_skills_libs_title,
                icon = { FaLayerGroup() },
                skills = listOf("Kobweb", "Compose Multiplatform", "Spring Boot", "React"),
                modifier = Modifier.tw("col-span-1")
            )

            AboutSkillGroup(
                title = Res.string.section_about_skills_tools_title,
                icon = { FaCode() },
                skills = listOf("IntelliJ IDEA", "Git", "Docker", "Postman"),
                modifier = Modifier.tw("col-span-1")
            )

            AboutContact(
                Modifier.tw("col-span-1")
            )
        }
    }
}
