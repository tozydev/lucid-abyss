package vn.id.tozydev.lucidabyss.components.layouts

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import vn.id.tozydev.lucidabyss.components.sections.Footer
import vn.id.tozydev.lucidabyss.components.sections.Navigation
import vn.id.tozydev.lucidabyss.models.PageLayoutData

val PageContentStyle =
    CssStyle {
        base {
            Modifier
                .fillMaxSize()
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
        document.title = "Kobweb - ${data.title}"
    }

    Box(
        Modifier
            .fillMaxWidth()
            .minHeight(100.vh)
            .padding(1.cssRem)
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Row)
            .gap(1.cssRem),
    ) {
        Navigation()

        Column(
            Modifier.flex(1).gap(1.cssRem),
        ) {
            Box(modifier = PageContentStyle.toModifier()) {
                content()
            }
            Footer()
        }
    }
}
