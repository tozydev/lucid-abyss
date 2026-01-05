package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaComments
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.ColumnIslandVariant
import vn.id.tozydev.lucidabyss.components.widgets.Island
import vn.id.tozydev.lucidabyss.styles.ColorVars
import vn.id.tozydev.lucidabyss.styles.TextXlStyle

@Composable
fun Discussion(modifier: Modifier = Modifier) {
    Island(
        modifier = modifier,
        variant = ColumnIslandVariant,
    ) {
        H3(
            TextXlStyle
                .toModifier()
                .fontWeight(FontWeight.SemiBold)
                .margin(bottom = 1.cssRem)
                .color(ColorVars.TextHeading.value())
                .toAttrs(),
        ) {
            Text("Thảo luận")
        }
        Div(
            Modifier
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Column)
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
                .borderRadius(0.5.cssRem)
                .gap(0.5.cssRem)
                .backgroundColor(ColorVars.BgContainer.value())
                .padding(2.cssRem)
                .toAttrs(),
        ) {
            FaComments(TextXlStyle.toModifier())
            SpanText("Chức năng thảo luận sẽ sớm được ra mắt!")
        }
    }
}
