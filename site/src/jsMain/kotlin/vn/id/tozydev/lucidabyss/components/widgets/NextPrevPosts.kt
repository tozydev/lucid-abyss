package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextTransform
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.cssRule
import com.varabyte.kobweb.silk.style.extendedBy
import com.varabyte.kobweb.silk.style.selectors.children
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.models.Post
import vn.id.tozydev.lucidabyss.models.nextPost
import vn.id.tozydev.lucidabyss.models.previousPost
import vn.id.tozydev.lucidabyss.styles.ColorVars
import vn.id.tozydev.lucidabyss.styles.TextXsModifier

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
                .gridTemplateAreas("prev next")
        }
    }

val NextPrevPostLinkVariant =
    UndecoratedLinkVariant.extendedBy({ IslandStyle.toModifier(ColumnIslandVariant, SoftLiftingIslandVariant) }) {
        base {
            Modifier.padding(1.5.cssRem)
        }

        children("span") {
            TextXsModifier
                .fontWeight(FontWeight.Medium)
                .color(ColorVars.TextLabel.value())
                .textTransform(TextTransform.Uppercase)
                .margin(bottom = 0.25.cssRem)
                .letterSpacing(0.05.cssRem)
        }

        children("h4") {
            Modifier.fontWeight(FontWeight.SemiBold).color(ColorVars.TextHeading.value())
        }

        cssRule(Breakpoint.MD, ".prev") {
            Modifier.gridArea("prev")
        }
        cssRule(Breakpoint.MD, ".next") {
            Modifier.gridArea("next")
        }
    }

@Composable
fun NextPrevPosts(
    post: Post,
    modifier: Modifier = Modifier,
) {
    val previousPost = post.previousPost
    val nextPost = post.nextPost
    Div(NextPrevPostsStyle.toModifier().then(modifier).toAttrs()) {
        if (previousPost != null) {
            Link(
                path = previousPost.route,
                modifier = Modifier.classNames("prev"),
                variant = NextPrevPostLinkVariant,
            ) {
                SpanText("Bài trước")
                H4 {
                    Text(previousPost.title)
                }
            }
        }
        if (nextPost != null) {
            Link(
                path = nextPost.route,
                modifier = Modifier.alignItems(AlignItems.FlexEnd).classNames("next"),
                variant = NextPrevPostLinkVariant,
            ) {
                SpanText("Bài sau")
                H4 {
                    Text(nextPost.title)
                }
            }
        }
    }
}
