package vn.id.tozydev.lucidabyss.components.layouts

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.boxClasses
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toAttrs
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.sections.Navigation
import vn.id.tozydev.lucidabyss.components.sections.PageFooter

data class PageLayoutData(
    val title: String,
)

val PageLayoutStyle =
    CssStyle {
        base {
            Modifier
                .fillMaxWidth()
                .minHeight(100.vh)
                .padding(1.cssRem)
                .display(DisplayStyle.Grid)
                .gridTemplateColumns {
                    size(auto)
                    size(1.fr)
                }.gap(1.cssRem)
        }
    }

@Composable
@Layout
fun PageLayout(
    ctx: PageContext,
    content: @Composable () -> Unit,
) {
    val data = ctx.data.getValue<PageLayoutData>()
    LaunchedEffect(data.title) {
        document.title = "${data.title} | tozydev"
    }

    Div(PageLayoutStyle.toAttrs()) {
        Navigation()

        Column(Modifier.gap(1.cssRem)) {
            Main(
                Modifier
                    .boxClasses()
                    .toAttrs(),
            ) {
                content()
            }
            PageFooter(Modifier.fillMaxWidth())
        }
    }
}
