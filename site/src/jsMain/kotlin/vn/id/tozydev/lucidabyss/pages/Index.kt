package vn.id.tozydev.lucidabyss.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.layouts.PageLayoutData
import vn.id.tozydev.lucidabyss.components.sections.Hero
import vn.id.tozydev.lucidabyss.components.widgets.Container

@InitRoute
fun initHomePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Home"))
}

@Page
@Composable
fun HomePage() {
    Div(
        Modifier
            .fillMaxSize()
            .display(DisplayStyle.Grid)
            .gridTemplateColumns { repeat(4) { minmax(0.px, 1.fr) } }
            .gridAutoRows { minmax(10.cssRem, auto) }
            .gap(1.cssRem)
            .gridAutoFlow(GridAutoFlow.Row)
            .gridTemplateAreas(
                "hero hero hero .",
                "working-project contact latest-posts latest-posts",
            ).toAttrs(),
    ) {
        Hero(
            Modifier
                .fillMaxSize()
                .gridArea("hero"),
        )

        /* Container(
            Modifier
                .fillMaxSize()
                .gridArea("quote"),
        ) {
            P {
                Text(
                    "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"",
                )
            }
        } */

        Container(
            Modifier
                .fillMaxSize()
                .gridArea("latest-posts"),
        ) {
            Column(Modifier.fillMaxSize()) {
                H2 { Text("Bài viết mới nhất") }
                P { Text("Mục này sẽ hiển thị các bài viết mới nhất trong tương lai.") }
            }
        }

        /* Container(
            Modifier
                .fillMaxSize()
                .gridArea("connect"),
        ) {
            Column(Modifier.fillMaxSize()) {
                H2 { Text("Kết nối với tôi") }
                Div(
                    Modifier
                        .fillMaxSize()
                        .display(DisplayStyle.Grid)
                        .gridTemplateColumns { repeat(2) { minmax(0.px, 1.fr) } }
                        .gap(0.5.cssRem)
                        .toAttrs(),
                ) {
                    ConnectLink(
                        url = "https://github.com/tozydev",
                        label = "GitHub",
                        icon = {
                            FaGithub(size = IconSize.LG)
                        },
                    )
                    ConnectLink(
                        url = "www.linkedin.com/in/tozydev",
                        label = "LinkedIn",
                        icon = {
                            FaLinkedin(size = IconSize.LG)
                        },
                    )
                    ConnectLink(
                        url = "mailto:me@tozydev.id.vn",
                        label = "Email",
                        icon = {
                            MdiEmail()
                        },
                    )
                    ConnectLink(
                        url = "https://discord.gg/invite/tozydev",
                        label = "Discord",
                        icon = {
                            FaDiscord(size = IconSize.LG)
                        },
                    )
                }
            }
        } */

        Container(
            Modifier
                .fillMaxSize()
                .gridArea("working-project"),
        ) {
            Column(Modifier.fillMaxSize()) {
                H2 { Text("Dự án đang phát triển") }
                P { Text("Mục này sẽ hiển thị các dự án đang phát triển trong tương lai.") }
            }
        }
    }
}

@Composable
private fun ConnectLink(
    url: String,
    label: String,
    icon: @Composable () -> Unit,
) {
    Link(url) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(0.5.cssRem)
                    .border(1.px, LineStyle.Solid, Colors.LightGray)
                    .borderRadius(1.cssRem)
                    .padding(1.cssRem)
                    .gap(0.5.cssRem),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            icon()
            SpanText(label)
        }
    }
}
