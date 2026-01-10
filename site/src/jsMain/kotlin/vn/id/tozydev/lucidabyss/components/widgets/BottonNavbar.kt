package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.silk.components.icons.fa.FaCubes
import com.varabyte.kobweb.silk.components.icons.fa.FaHouse
import com.varabyte.kobweb.silk.components.icons.fa.FaRss
import com.varabyte.kobweb.silk.components.icons.fa.FaUser
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

@Composable
fun BottonNavbar(modifier: Modifier = Modifier) {
    val ctx = rememberPageContext()

    @Composable
    fun MenuItem(
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

        Li(
            Modifier
                .tw("flex-1 items-center")
                .toAttrs {
                    if (isActive) {
                        tw("menu-active")
                    }
                },
        ) {
            Anchor(
                href = path,
                attrs = { tw("size-full justify-center") },
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(0.125.cssRem),
                ) {
                    icon()
                    Text(label)
                }
            }
        }
    }

    Nav(
        Modifier
            .tw("navbar bg-base-100 fixed z-50 left-0 right-0 bottom-0 mx-auto min-h-auto p-0 md:hidden")
            .then(modifier)
            .toAttrs(),
    ) {
        Ul({ tw("menu menu-sm menu-horizontal gap-1 w-full font-medium pb-0") }) {
            MenuItem("/", "Trang chủ") {
                FaHouse()
            }
            MenuItem("/me", "Về tôi") {
                FaUser()
            }
            MenuItem("/blog/", "Blog") {
                FaRss()
            }
            MenuItem("/san-pham", "Sản phẩm") {
                FaCubes()
            }
        }
    }
}
