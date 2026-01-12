package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaUser
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.models.Constants.EMAIL_HASH
import vn.id.tozydev.lucidabyss.utils.getGravatarUrl
import vn.id.tozydev.lucidabyss.utils.navigateToBlog
import vn.id.tozydev.lucidabyss.utils.navigateToMe
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun Hero(modifier: Modifier = Modifier) {
    Div(
        Modifier
            .tw("card card-lg card-border bg-base-100")
            .then(modifier)
            .toAttrs(),
    ) {
        Div({ tw("card-body") }) {
            HeroAvatar()
            Div({ tw("mt-4 mb-6") }) {
                H1({ tw("font-bold text-3xl md:text-4xl mb-3") }) {
                    Text("Xin chào, tôi là")
                    Br()
                    Span({ tw("text-4xl md:text-5xl text-primary") }) {
                        Text("Thanh Tân")
                    }
                }
                P({ tw("text-base md:text-lg") }) {
                    Text("Hay còn được gọi là tozydev, một developer. Với tôi, code là một đam mê, nó tuyệt vời hơn khi tôi code với ")
                    Span({ tw("font-semibold text-transparent bg-clip-text bg-(image:--color-kotlin)") }) {
                        Text("Kotlin")
                    }
                    Text(". Và blog này, nơi tôi chia sẽ nhưng câu chuyện của mình...")
                }
            }
            HeroActions()
        }
    }
}

@Composable
private fun HeroActions() {
    val ctx = rememberPageContext()
    Div({ tw("card-actions") }) {
        Button(
            {
                tw("btn btn-primary hover:scale-105 transition-transform")
                onClick {
                    ctx.router.navigateToMe()
                }
            },
        ) {
            FaUser(Modifier.tw("mr-2"))
            Text("Tìm hiểu thêm")
        }
        Button(
            {
                tw("btn")
                onClick { ctx.router.navigateToBlog() }
            },
        ) {
            Text("Xem blog")
        }
    }
}

@Composable
private fun HeroAvatar() {
    Img(
        src = getGravatarUrl(EMAIL_HASH, 96),
        alt = "Avatar",
        { tw("size-24 border-2 border-base-300 rounded-field transition hover:scale-105 hover:drop-shadow-md") },
    )
}
