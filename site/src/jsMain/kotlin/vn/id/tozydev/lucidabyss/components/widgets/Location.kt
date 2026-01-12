package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaLocationDot
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun Location(modifier: Modifier = Modifier) {
    Div(Modifier.tw("card card-border bg-base-100").then(modifier).toAttrs()) {
        Div({ tw("card-body text-center justify-center items-center") }) {
            Div({ tw("size-10 rounded-full bg-accent text-accent-content flex items-center justify-center shadow-lg animate-bounce") }) {
                FaLocationDot()
            }
            H4({ tw("font-bold") }) {
                Text("Hồ Chí Minh, Việt Nam")
            }
            P({ tw("text-sm font-medium") }) {
                Text("Đang học tại UTH")
            }
        }
    }
}
