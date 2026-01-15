package vn.id.tozydev.lucidabyss.pages.blog

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.layouts.PageLayoutData
import vn.id.tozydev.lucidabyss.components.widgets.PostCard
import vn.id.tozydev.lucidabyss.generated.filePathToPost
import vn.id.tozydev.lucidabyss.utils.tw

@InitRoute
fun initBlogPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Blog"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun BlogPage() {
    Div({ tw("flex flex-col gap-8") }) {
        BlogHeader()
        Div({ tw("grid gap-6 grid-cols-1 md:grid-cols-2 lg:grid-cols-3") }) {
            filePathToPost.values.forEach {
                PostCard(it)
            }
        }
    }
}

@Composable
private fun BlogHeader() {
    Div({ tw("card card-xl card-border bg-base-100") }) {
        Div({ tw("card-body gap-4") }) {
            H1({ tw("text-3xl md:text-4xl font-bold") }) {
                Text("Chia sẻ kiến thức")
                Br()
                Span({ tw("text-primary") }) {
                    Text("Lập trình và cuộc sống")
                }
            }
            P {
                Text("Nơi lưu lại những bài học, kinh nghiệm và những điều thú vị trong hành trình phát triển bản thân để trở nên tốt hơn.")
            }
        }
    }
}
