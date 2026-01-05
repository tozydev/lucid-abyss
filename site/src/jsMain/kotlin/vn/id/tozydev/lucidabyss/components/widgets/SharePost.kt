package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaBluesky
import com.varabyte.kobweb.silk.components.icons.fa.FaFacebook
import com.varabyte.kobweb.silk.components.icons.fa.FaLink
import com.varabyte.kobweb.silk.components.icons.fa.FaLinkedin
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.styles.TextSmStyle

@Composable
fun SharePost(modifier: Modifier = Modifier) {
    Island(modifier = Modifier.padding(1.5.cssRem) then modifier) {
        H3(
            TextSmStyle
                .toModifier()
                .fontWeight(FontWeight.Bold)
                .margin(bottom = 0.5.cssRem)
                .toAttrs(),
        ) {
            Text("Chia sẻ bài viết")
        }
        // todo update share links later
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(1.cssRem),
        ) {
            Link("") {
                FaFacebook()
            }
            Link("") {
                FaBluesky()
            }
            Link("") {
                FaLinkedin()
            }
            Link("") {
                FaLink()
            }
        }
    }
}
