package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.dom.GenericTag
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRight
import com.varabyte.kobweb.silk.components.icons.fa.FaNewspaper
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toAttrs
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement
import vn.id.tozydev.lucidabyss.components.widgets.ColumnIslandVariant
import vn.id.tozydev.lucidabyss.components.widgets.Island
import vn.id.tozydev.lucidabyss.styles.Text2XlStyle
import vn.id.tozydev.lucidabyss.styles.TextSmStyle

@Composable
fun LatestPost(modifier: Modifier = Modifier) {
    Island(
        modifier = modifier,
        variant = ColumnIslandVariant,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            H2(TextSmStyle.toAttrs()) {
                FaNewspaper(Modifier.margin(right = 0.5.cssRem))
                Text("Bài viết mới nhất")
            }
            GenericTag<HTMLElement>(
                name = "time",
                attrs =
                    TextSmStyle.toAttrs {
                        attr("datetime", "2026-01-01")
                    },
            ) {
                Text("Jan 1, 2026")
            }
        }
        Column {
            H3(Text2XlStyle.toAttrs()) {
                Text("Tôi chọn Kotlin và Kobweb cho website của mình")
            }
            P {
                Text(
                    "Kotlin là một ngôn ngữ lập trình hiện đại, mạnh mẽ và dễ sử dụng. Kobweb là một framework tuyệt vời để xây dựng các ứng dụng web với Kotlin...",
                )
            }
            Link("#") {
                SpanText("Đọc thêm...", Modifier.margin(right = 0.25.cssRem))
                FaArrowRight()
            }
        }
    }
}
