package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.LinkStyle
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

val NextPrevPostsStyle =
    CssStyle {
        base {
            Modifier
                .display(DisplayStyle.Grid)
                .gridTemplateColumns { repeat(1) { minmax(0.px, 1.fr) } }
                .gap(1.cssRem)
        }
        Breakpoint.MD {
            Modifier
                .gridTemplateColumns { repeat(2) { minmax(0.px, 1.fr) } }
        }
    }

val NextPrevPostLinkVariant =
    LinkStyle.addVariant({ IslandStyle.toModifier(ColumnIslandVariant) }) {
        base {
            Modifier
                .textDecorationLine(TextDecorationLine.None)
                .padding(1.5.cssRem)
        }
    }

@Composable
fun NextPrevPosts(modifier: Modifier = Modifier) {
    Div(NextPrevPostsStyle.toModifier().then(modifier).toAttrs()) {
        Link(
            path = "",
            variant = UndecoratedLinkVariant.then(UncoloredLinkVariant).then(NextPrevPostLinkVariant),
        ) {
            SpanText("Bài trước")
            H4 {
                Text("Tiêu đề bài viết trước")
            }
        }
        Link(
            path = "",
            modifier = Modifier.alignItems(AlignItems.FlexEnd),
            variant = UndecoratedLinkVariant.then(UncoloredLinkVariant).then(NextPrevPostLinkVariant),
        ) {
            SpanText("Bài sau")
            H4 {
                Text("Tiêu đề bài viết sau")
            }
        }
    }
}
