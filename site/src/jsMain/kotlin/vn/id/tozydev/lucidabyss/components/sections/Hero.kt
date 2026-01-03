package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.BackgroundClip
import com.varabyte.kobweb.compose.css.BoxShadow
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonSize
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaUser
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.addVariantBase
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.extendedBy
import com.varabyte.kobweb.silk.style.selectors.children
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.animation.TransitionDurationVars
import com.varabyte.kobweb.silk.style.vars.size.BorderRadiusVars
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.ColumnIslandVariant
import vn.id.tozydev.lucidabyss.components.widgets.Island
import vn.id.tozydev.lucidabyss.components.widgets.IslandStyle
import vn.id.tozydev.lucidabyss.models.Constants.EMAIL_HASH
import vn.id.tozydev.lucidabyss.styles.ColorVars
import vn.id.tozydev.lucidabyss.styles.PrimaryButtonVariant
import vn.id.tozydev.lucidabyss.styles.Text2XlStyle
import vn.id.tozydev.lucidabyss.styles.Text3XlModifier
import vn.id.tozydev.lucidabyss.styles.TextLgModifier
import vn.id.tozydev.lucidabyss.utils.getGravatarUrl
import vn.id.tozydev.lucidabyss.utils.kotlinGradient
import vn.id.tozydev.lucidabyss.utils.navigateToAbout
import vn.id.tozydev.lucidabyss.utils.navigateToBlog

val HeroIslandVariant =
    IslandStyle.addVariantBase {
        Modifier
            .justifyContent(JustifyContent.SpaceBetween)
    }

val HeroHeadlineStyle =
    Text2XlStyle.extendedBy {
        base {
            Modifier
                .color(ColorVars.TextHeading.value())
                .fontWeight(FontWeight.Bold)
                .margin(bottom = 0.75.cssRem)
        }
        children("span") {
            Text3XlModifier
                .color(ColorVars.Primary.value())
                .fontWeight(FontWeight.ExtraBold)
        }
    }

val HeroDescriptionStyle =
    CssStyle {
        base {
            Modifier
                .margin(bottom = 1.5.cssRem)
        }

        Breakpoint.MD {
            TextLgModifier
        }

        cssRule(" .kotlin") {
            Modifier
                .backgroundImage(kotlinGradient())
                .background { clip(BackgroundClip.Text) }
                .color(Colors.Transparent)
                .fontWeight(FontWeight.SemiBold)
        }
    }

@Composable
fun Hero(modifier: Modifier = Modifier) {
    Island(
        modifier = modifier,
        variant = ColumnIslandVariant.then(HeroIslandVariant),
    ) {
        HeroAvatar()

        Div {
            H1(HeroHeadlineStyle.toAttrs()) {
                Text("Xin chào, tôi là")
                Br()
                SpanText("Thanh Tân")
            }
            P(HeroDescriptionStyle.toAttrs()) {
                Text("Hay còn được gọi là tozydev, một developer. Với tôi, code là một đam mê, nó tuyệt vời hơn khi tôi code với ")
                SpanText(
                    "Kotlin",
                    modifier = Modifier.classNames("kotlin"),
                )
                Text(". Và blog này, nơi tôi chia sẽ nhưng câu chuyện của mình...")
            }
        }

        HeroActions()
    }
}

val HeroPrimaryButtonVariant =
    PrimaryButtonVariant.extendedBy {
        base {
            Modifier
                .transition {
                    property(TransitionProperty.All)
                    duration(TransitionDurationVars.Fast.value())
                    timingFunction(TransitionTimingFunction.cubicBezier(0.4, 0.0, 0.2, 1.0))
                }
        }
        hover {
            Modifier
                .scale(1.05f)
        }
    }

@Composable
private fun HeroActions() {
    val ctx = rememberPageContext()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(0.75.cssRem),
    ) {
        Button(
            modifier = Modifier.fontSize(1.cssRem),
            onClick = { ctx.router.navigateToAbout() },
            variant = PrimaryButtonVariant then HeroPrimaryButtonVariant,
            size = ButtonSize.LG,
        ) {
            FaUser(Modifier.margin(right = 0.5.cssRem))
            Text("Tìm hiểu thêm")
        }

        Button(
            modifier = Modifier.fontSize(1.cssRem),
            onClick = { ctx.router.navigateToBlog() },
            size = ButtonSize.LG,
        ) {
            Text("Xem bài viết")
        }
    }
}

val HeroAvatarStyle =
    CssStyle {
        base {
            Modifier
                .size(6.cssRem)
                .borderRadius(BorderRadiusVars.MD.value())
                .transition {
                    property(TransitionProperty.All)
                    duration(TransitionDurationVars.Fast.value())
                    timingFunction(TransitionTimingFunction.cubicBezier(0.25, 0.8, 0.25, 1.0))
                }.border(2.px, LineStyle.Solid, ColorVars.OutlineVariant.value())
                .margin(bottom = 1.cssRem)
        }
        hover {
            Modifier
                .scale(1.05f)
                .boxShadow(
                    BoxShadow.of(0.px, 4.px, 8.px, 3.px, Color.rgba(0, 0, 0, 0.15f)),
                )
        }
    }

@Composable
private fun HeroAvatar() {
    Image(
        src = getGravatarUrl(EMAIL_HASH, 96),
        alt = "Avatar",
        modifier = HeroAvatarStyle.toModifier(),
    )
}
