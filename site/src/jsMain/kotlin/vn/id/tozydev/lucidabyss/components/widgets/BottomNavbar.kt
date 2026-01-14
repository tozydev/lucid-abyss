package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaCubes
import com.varabyte.kobweb.silk.components.icons.fa.FaHouse
import com.varabyte.kobweb.silk.components.icons.fa.FaRss
import com.varabyte.kobweb.silk.components.icons.fa.FaUser
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun BottomNavbar(modifier: Modifier = Modifier) {
    val ctx = rememberPageContext()

    @Composable
    fun DockItem(
        path: String,
        label: String,
        icon: @Composable () -> Unit,
    ) {
        val isActive =
            if (path == "/") {
                ctx.route.path == "/"
            } else {
                ctx.route.path.startsWith(path)
            }

        Button(
            Modifier
                .onClick {
                    ctx.router.navigateTo(path)
                }.toAttrs {
                    if (isActive) {
                        tw("dock-active")
                    }
                },
        ) {
            Span({ tw("size-[1.2em]") }) {
                icon()
            }
            Span({ tw("dock-label") }) {
                Text(label)
            }
        }
    }

    Div(
        Modifier
            .tw("dock dock-xs md:hidden")
            .then(modifier)
            .toAttrs(),
    ) {
        DockItem("/", "Trang chủ") {
            FaHouse()
        }
        DockItem("/me", "Về tôi") {
            FaUser()
        }
        DockItem("/blog/", "Blog") {
            FaRss()
        }
        DockItem("/san-pham", "Sản phẩm") {
            FaCubes()
        }
    }
}
