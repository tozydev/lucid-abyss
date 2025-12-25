package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.BackgroundClip
import com.varabyte.kobweb.compose.css.BoxShadow
import com.varabyte.kobweb.compose.css.CSSPosition
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.css.functions.RadialGradient
import com.varabyte.kobweb.compose.css.functions.radialGradient
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonSize
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.graphics.ImageDecoding
import com.varabyte.kobweb.silk.components.graphics.ImageLoading
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.extendedByBase
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.Container
import vn.id.tozydev.lucidabyss.models.Constants.EMAIL_HASH
import vn.id.tozydev.lucidabyss.styles.TypeDisplayStyle
import vn.id.tozydev.lucidabyss.styles.TypeTitleStyle
import vn.id.tozydev.lucidabyss.theme.colorScheme
import vn.id.tozydev.lucidabyss.theme.toColorScheme
import vn.id.tozydev.lucidabyss.utils.getGravatarUrl

val HeroDisplayStyle =
    TypeDisplayStyle.extendedByBase {
        Modifier
            .fontWeight(FontWeight.Bold)
            .margin(bottom = 1.cssRem)
    }

@Composable
fun Hero(modifier: Modifier = Modifier) {
    val ctx = rememberPageContext()
    val colorScheme = ColorMode.current.toColorScheme()
    Container(
        Modifier
            .gridTemplateColumns {
                size(1.fr)
                size(auto)
            }.gap(2.cssRem)
            .then(modifier),
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .gridArea("auto"),
        ) {
            P(
                TypeTitleStyle
                    .toModifier()
                    .margin(bottom = 1.cssRem)
                    .toAttrs(),
            ) {
                Text("Hi there 👋 Tôi là")
            }
            H1(
                attrs = HeroDisplayStyle.toAttrs(),
            ) {
                SpanText("Thanh Tân", Modifier.color(colorScheme.primary))
            }
            P(
                Modifier
                    .flex(1)
                    .margin(bottom = 1.5.cssRem)
                    .toAttrs(),
            ) {
                Text(
                    "Hay còn được gọi là tozydev, một developer. Với tôi, code là một đam mê, nó tuyệt vời hơn khi tôi code với ",
                )
                SpanText(
                    "Kotlin",
                    modifier =
                        Modifier
                            .backgroundImage(
                                radialGradient(
                                    shape = RadialGradient.Shape.Circle(RadialGradient.Extent.FarthestSide),
                                    position = CSSPosition.TopRight,
                                ) {
                                    add(Color.rgb(0xE44857))
                                    add(Color.rgb(0xC711E1), 50.4494.percent)
                                    add(Color.rgb(0x7F52FF), 100.percent)
                                },
                            ).background { clip(BackgroundClip.Text) }
                            .color(Colors.Transparent)
                            .fontWeight(FontWeight.Bold),
                )
                Text(". Và blog này, nơi tôi chia sẽ nhưng câu chuyện của mình...")
            }
            Button(
                onClick = { ctx.router.navigateTo("/about") },
                size = ButtonSize.LG,
                modifier =
                    Modifier
                        .borderRadius(1.cssRem)
                        .backgroundColor(colorScheme.primaryContainer)
                        .color(colorScheme.onPrimaryContainer),
            ) {
                SpanText("Tìm hiểu thêm về tôi")
            }
        }
        Column(
            Modifier
                .fillMaxSize()
                .gridArea("auto"),
        ) {
            HeroAvatar()
        }
    }
}

val HeroAvatarStyle =
    CssStyle {
        base {
            Modifier
                .size(16.cssRem)
                .borderRadius(50.percent)
                .objectFit(ObjectFit.Cover)
                .border(3.px, LineStyle.Solid, colorScheme.outlineVariant)
                .transition {
                    property(TransitionProperty.All)
                    duration(0.3.s)
                    timingFunction(TransitionTimingFunction.cubicBezier(0.25, 0.8, 0.25, 1.0))
                }
        }
        hover {
            Modifier
                .border(3.px, LineStyle.Solid, colorScheme.primary)
                .scale(1.05f)
                .boxShadow(
                    BoxShadow.of(0.px, 4.px, 8.px, 3.px, Color.rgba(0, 0, 0, 0.15f)),
                )
        }
    }

@Composable
private fun HeroAvatar() {
    Image(
        src = getGravatarUrl(EMAIL_HASH, size = 256),
        alt = "tozydev's avatar",
        loading = ImageLoading.Lazy,
        decoding = ImageDecoding.Auto,
        modifier = HeroAvatarStyle.toModifier(),
    )
}
