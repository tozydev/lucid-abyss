package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaLocationDot
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.Island

@Composable
fun Location(modifier: Modifier = Modifier) {
    Island(
        modifier = modifier,
    ) {
        Image(
            src = "https://images.unsplash.com/photo-1569336415962-a4bd9f69cd83?q=80&w=1000&auto=format&fit=crop",
            alt = "Location Image",
            modifier =
                Modifier
                    .fillMaxSize()
                    .objectFit(ObjectFit.Cover)
                    .opacity(0.6f)
                    .position(Position.Absolute)
                    .top(0.px)
                    .left(0.px),
        )
        Column(
            modifier =
                Modifier
                    .padding(2.cssRem)
                    .background {
                        color(Color.rgba(0, 0, 0, 0.3f))
                    },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            FaLocationDot()
            H4 {
                Text("Hồ Chí Minh, Việt Nam")
            }
            P {
                Text("Đang học tại UTH")
            }
        }
    }
}
