package vn.id.tozydev.lucidabyss.components.home

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.navigation.BasePath
import com.varabyte.kobweb.silk.components.icons.fa.FaUser
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths
import vn.id.tozydev.lucidabyss.strings.Strings
import vn.id.tozydev.lucidabyss.strings.description
import vn.id.tozydev.lucidabyss.strings.title
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
context(_: SiteLanguage)
fun HomeHero(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw(
                "card card-lg card-border bg-base-100 hover:border-primary hover:shadow-[0_0_20px_-5px_var(--color-primary)] transition-all duration-300",
            ).then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body") }) {
            HeroAvatar()
            Div({ tw("mt-4 mb-6") }) {
                H1({ tw("font-bold text-3xl md:text-4xl mb-3") }) {
                    Strings.section.hero.title { first, second ->
                        Text(first)
                        Br()
                        Span({ tw("text-4xl md:text-5xl text-primary") }) {
                            Text(second)
                        }
                    }
                }
                P({ tw("text-base md:text-lg") }) {
                    Strings.section.hero.description { first, kotlin, second ->
                        Text(first)
                        Span({ tw("font-semibold text-transparent bg-clip-text bg-(image:--color-kotlin)") }) {
                            Text(kotlin)
                        }
                        Text(second)
                    }
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
            Text(Strings.section.hero.learnMore)
        }
        Button(
            {
                tw("btn")
                onClick { ctx.router.navigateTo(SitePaths.blog) }
            },
        ) {
            Text(Strings.section.hero.viewBlog)
        }
    }
}

@Composable
private fun HeroAvatar() {
    Img(
        src = BasePath.prependTo("/images/avatar_96x.webp"),
        alt = Strings.section.hero.imageAlt,
    ) {
        tw("size-24 border-2 border-base-300 rounded-field transition hover:scale-105 hover:drop-shadow-md")
    }
}
