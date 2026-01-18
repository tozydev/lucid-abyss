package vn.id.tozydev.lucidabyss.components.layouts

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.layout.Layout
import kotlinx.browser.document
import kotlinx.dom.appendElement
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLMetaElement
import vn.id.tozydev.lucidabyss.components.sections.SiteFooter
import vn.id.tozydev.lucidabyss.components.sections.SiteHeader
import vn.id.tozydev.lucidabyss.components.widgets.BackToTopButton
import vn.id.tozydev.lucidabyss.components.widgets.BottomNavbar
import vn.id.tozydev.lucidabyss.core.SiteLanguage
import vn.id.tozydev.lucidabyss.pages.Page
import vn.id.tozydev.lucidabyss.strings.strings
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
@Layout
fun PageLayout(
    ctx: PageContext,
    content: @Composable () -> Unit,
) {
    val pageProperties = ctx.data.getValue<Page.Properties>()
    val language = ctx.data.getValue<SiteLanguage>()

    LaunchedEffect(pageProperties.title) {
        document.title = "${pageProperties.title} | tozydev"
        document.head!!.apply {
            appendElement("meta") {
                this as HTMLMetaElement
                this.name = "description"
                this.content = pageProperties.description
            }
        }
    }

    context(language, language.strings()) {
        SiteHeader()

        BottomNavbar()

        Main({ tw("px-4 pt-26 pb-4 max-w-6xl mx-auto") }) {
            content()
        }

        SiteFooter(Modifier.tw("max-w-6xl mx-auto mb-12 p-4"))

        BackToTopButton()
    }
}
