package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.BackgroundClip
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextTransform
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaJava
import com.varabyte.kobweb.silk.components.icons.fa.FaLayerGroup
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.extendedBy
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.animation.TransitionDurationVars
import com.varabyte.kobweb.silk.theme.colors.ColorPalettes
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.Badge
import vn.id.tozydev.lucidabyss.components.widgets.BadgeVars
import vn.id.tozydev.lucidabyss.components.widgets.ColumnIslandVariant
import vn.id.tozydev.lucidabyss.components.widgets.Island
import vn.id.tozydev.lucidabyss.components.widgets.NoneTransformBadgeVariant
import vn.id.tozydev.lucidabyss.styles.ColorVars
import vn.id.tozydev.lucidabyss.styles.TextSmStyle
import vn.id.tozydev.lucidabyss.utils.kotlinGradient

@Composable
fun TechStack(modifier: Modifier = Modifier) {
    Island(
        modifier = modifier,
        variant = ColumnIslandVariant,
    ) {
        Row(
            modifier = Modifier.margin(bottom = 1.cssRem),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            H2(
                TextSmStyle
                    .toModifier()
                    .color(ColorVars.TextLabel.value())
                    .fontWeight(FontWeight.SemiBold)
                    .textTransform(TextTransform.Uppercase)
                    .toAttrs(),
            ) {
                FaLayerGroup(Modifier.margin(right = 0.5.cssRem))
                Text("Công nghệ sử dụng")
            }
        }
        Row(
            modifier = Modifier.flexWrap(FlexWrap.Wrap).gap(1.cssRem),
        ) {
            TechItem(
                label = "Java",
                icon = { FaJava(Modifier.color(ColorPalettes.Orange._500)) },
            )
            TechItem(
                label = "Kotlin",
                icon = {
                    SpanText(
                        "K",
                        Modifier
                            .fontSize(1.5.cssRem)
                            .fontWeight(FontWeight.Bold)
                            .backgroundImage(kotlinGradient())
                            .color(Colors.Transparent)
                            .background { clip(BackgroundClip.Text) },
                    )
                },
            )
        }
    }
}

val TechItemBadgeVariant =
    NoneTransformBadgeVariant.extendedBy({ TextSmStyle.toModifier() }) {
        base {
            Modifier
                .fontWeight(FontWeight.Medium)
                .display(DisplayStyle.Flex)
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
                .gap(0.5.cssRem)
                .padding(topBottom = 0.5.cssRem, leftRight = 0.75.cssRem)
                .setVariable(BadgeVars.Color, ColorVars.TextBody.value())
                .setVariable(BadgeVars.BackgroundColor, Colors.Transparent)
                .setVariable(BadgeVars.BorderColor, ColorVars.OutlineVariant.value())
                .transition {
                    property(TransitionProperty.All)
                    duration(TransitionDurationVars.Fast.value())
                    timingFunction(TransitionTimingFunction.cubicBezier(0.25, 0.8, 0.25, 1.0))
                }
        }
        hover {
            Modifier
                .fontWeight(FontWeight.SemiBold)
                .setVariable(BadgeVars.BackgroundColor, ColorVars.BgHighlight.value())
                .setVariable(BadgeVars.BorderColor, ColorVars.Primary.value())
        }
    }

@Composable
fun TechItem(
    label: String,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Badge(modifier, TechItemBadgeVariant) {
        icon()
        Text(label)
    }
}
