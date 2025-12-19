package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.functions.clamp
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonSize
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.Container
import vn.id.tozydev.lucidabyss.theme.toColorScheme

@Composable
fun Hero(modifier: Modifier = Modifier) {
    val ctx = rememberPageContext()
    val colorScheme = ColorMode.current.toColorScheme()
    Container(modifier) {
        Column(
            Modifier.fillMaxSize(),
        ) {
            SpanText(
                "🚧 Đang xây dựng...",
                Modifier
                    .backgroundColor(colorScheme.tertiaryContainer)
                    .color(colorScheme.onTertiaryContainer)
                    .fontSize(0.875.cssRem)
                    .fontWeight(500)
                    .padding(0.5.cssRem)
                    .borderRadius(0.5.cssRem)
                    .margin(bottom = 1.cssRem),
            )
            H1(
                attrs =
                    Modifier
                        .fontSize(clamp(2.cssRem, 5.vw, 3.75.cssRem))
                        .margin(bottom = 0.5.cssRem)
                        .toAttrs(),
            ) {
                SpanText("Thanh Tân", Modifier.color(colorScheme.primary))
            }
            P(Modifier.flex(1).toAttrs()) {
                Text(
                    "Xin chào, cảm ơn vì đã ghé thăm. Tôi là Thanh Tân, một developer. Với tôi, code là một đam mê, nó tuyệt vời hơn khi tôi code với Kotlin. Và blog này, nơi tôi chia sẽ nhưng câu chuyện của mình...",
                )
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
    }
}
