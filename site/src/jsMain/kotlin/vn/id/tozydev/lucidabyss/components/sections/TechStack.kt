package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.icons.fa.FaJava
import com.varabyte.kobweb.silk.components.icons.fa.FaLayerGroup
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.ColumnIslandVariant
import vn.id.tozydev.lucidabyss.components.widgets.Island

@Composable
fun TechStack(modifier: Modifier = Modifier) {
    Island(
        modifier = modifier,
        variance = ColumnIslandVariant,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            H2 {
                FaLayerGroup(Modifier.margin(right = 0.5.cssRem))
                Text("Công nghệ sử dụng")
            }
        }
        Row(
            modifier = Modifier.flexWrap(FlexWrap.Wrap).gap(1.cssRem),
        ) {
            TechItem("Java", { FaJava() })
            TechItem(
                "Kotlin",
                {
                    SpanText("K", Modifier.fontSize(1.5.cssRem).fontWeight(FontWeight.Bold))
                },
            )
            TechItem("Java", { FaJava() })
            TechItem("Java", { FaJava() })
            TechItem("Java", { FaJava() })
            TechItem("Java", { FaJava() })
            TechItem("Java", { FaJava() })
            TechItem("Java", { FaJava() })
            TechItem("Java", { FaJava() })
        }
    }
}

val TechItemStyle =
    CssStyle.base {
        Modifier
            .borderRadius(0.5.cssRem)
            .border(1.px, LineStyle.Solid, Colors.Gray) // todo: use theme color
            .backgroundColor(Colors.LightGray) // todo: use theme color
            .padding(topBottom = 0.5.cssRem, leftRight = 0.75.cssRem)
    }

@Composable
fun TechItem(
    label: String,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = TechItemStyle.toModifier().then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(0.5.cssRem),
    ) {
        icon()
        Text(label)
    }
}
