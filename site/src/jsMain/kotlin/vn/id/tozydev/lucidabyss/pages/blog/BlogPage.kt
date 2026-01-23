package vn.id.tozydev.lucidabyss.pages.blog

import Res
import androidx.compose.runtime.*
import com.varabyte.kobweb.core.PageContext
import org.jetbrains.compose.web.dom.*
import strings.StringsEn
import strings.StringsVi
import vn.id.tozydev.lucidabyss.components.PostCard
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.core.SitePaths
import vn.id.tozydev.lucidabyss.generated.BlogPosts
import vn.id.tozydev.lucidabyss.pages.Page
import vn.id.tozydev.lucidabyss.utils.tw

class BlogPage(
    language: SiteLanguage,
) : Page(language) {
    override val route = SitePaths.BLOG_PATH
    override val properties =
        when (language) {
            SiteLanguage.En -> {
                Properties(
                    title = StringsEn.page_blog_title,
                    description = StringsEn.page_blog_description,
                )
            }

            else -> {
                Properties(
                    title = StringsVi.page_blog_title,
                    description = StringsVi.page_blog_description,
                )
            }
        }

    @Composable
    override fun content(ctx: PageContext) {
        Div({ tw("flex flex-col gap-8") }) {
            BlogHeader()
            Div({ tw("grid gap-6 grid-cols-1 md:grid-cols-2 lg:grid-cols-3") }) {
                BlogPosts[language]?.forEach {
                    PostCard(it)
                }
            }
        }
    }

    @Composable
    private fun BlogHeader() {
        Div({ tw("card card-xl card-border bg-base-100") }) {
            Div({ tw("card-body gap-4") }) {
                H1({ tw("text-3xl md:text-4xl font-bold") }) {
                    Text(Res.string.page_blog_header_title_first)
                    Br()
                    Span({ tw("text-primary") }) {
                        Text(Res.string.page_blog_header_title_second)
                    }
                }
                P {
                    Text(Res.string.page_blog_header_description)
                }
            }
        }
    }
}
