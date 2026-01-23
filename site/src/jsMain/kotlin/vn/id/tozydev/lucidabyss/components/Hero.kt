package vn.id.tozydev.lucidabyss.components

import Res
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaUser
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths
import vn.id.tozydev.lucidabyss.models.Constants.EMAIL_HASH
import vn.id.tozydev.lucidabyss.utils.getGravatarUrl
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
context(_: SiteLanguage)
fun Hero(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw("card card-lg card-border bg-base-100")
            .then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body") }) {
            HeroAvatar()
            Div({ tw("mt-4 mb-6") }) {
                H1({ tw("font-bold text-3xl md:text-4xl mb-3") }) {
                    Text(Res.string.section_hero_title_first)
                    Br()
                    Span({ tw("text-4xl md:text-5xl text-primary") }) {
                        Text(Res.string.section_hero_title_second)
                    }
                }
                P({ tw("text-base md:text-lg") }) {
                    Text(Res.string.section_hero_description_first)
                    Span({ tw("font-semibold text-transparent bg-clip-text bg-(image:--color-kotlin)") }) {
                        Text("Kotlin")
                    }
                    Text(Res.string.section_hero_description_second)
                }
            }
            HeroActions()
        }
    }
}

@Composable
context(_: SiteLanguage)
private fun HeroActions() {
    val ctx = rememberPageContext()
    Div({ tw("card-actions") }) {
        Button(
            {
                tw("btn btn-primary hover:scale-105 transition-transform")
                onClick { ctx.router.navigateTo(SitePaths.about) }
            },
        ) {
            FaUser(Modifier.tw("mr-2"))
            Text(Res.string.section_hero_learn_more)
        }
        Button(
            {
                tw("btn")
                onClick { ctx.router.navigateTo(SitePaths.blog) }
            },
        ) {
            Text(Res.string.section_hero_view_blog)
        }
    }
}

@Composable
private fun HeroAvatar() {
    Img(
        src = getGravatarUrl(EMAIL_HASH, 96),
        alt = Res.string.section_hero_image_alt,
        { tw("size-24 border-2 border-base-300 rounded-field transition hover:scale-105 hover:drop-shadow-md") },
    )
}
