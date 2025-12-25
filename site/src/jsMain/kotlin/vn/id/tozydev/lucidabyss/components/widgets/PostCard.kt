package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.graphics.ImageDecoding
import com.varabyte.kobweb.silk.components.graphics.ImageLoading
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.MonthNames
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.elements.Time
import vn.id.tozydev.lucidabyss.models.Post
import vn.id.tozydev.lucidabyss.models.coverImagePathOrDefault
import vn.id.tozydev.lucidabyss.styles.TypeLabelStyle
import vn.id.tozydev.lucidabyss.styles.TypeTitleStyle

val format =
    DateTimeComponents.Format {
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        chars(" ")
        day()
        chars(", ")
        year()
    }

@Composable
fun PostCard(
    post: Post,
    modifier: Modifier = Modifier,
) {
    val pageCtx = rememberPageContext()
    Container(
        Modifier
            .padding(0.px)
            .onClick {
                pageCtx.router.navigateTo(post.route)
            }.then(modifier),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                post.coverImagePathOrDefault,
                alt = "Cover image for ${post.title}",
                loading = ImageLoading.Lazy,
                decoding = ImageDecoding.Auto,
                modifier =
                    Modifier
                        .size(100.percent, 15.cssRem)
                        .objectFit(ObjectFit.Cover)
                        .borderRadius(0.25.cssRem)
                        .borderRadius(2.cssRem)
                        .flexShrink(0),
            )
            Column(
                Modifier
                    .padding(top = 1.cssRem, leftRight = 1.5.cssRem, bottom = 1.5.cssRem)
                    .gap(0.5.cssRem)
                    .fillMaxSize(),
            ) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .gap(0.25.cssRem)
                            .margin(bottom = 0.5.cssRem),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Time(datetime = post.publishedAt.toString(), TypeLabelStyle.toAttrs()) {
                        Text(post.publishedAt.format(format))
                    }
                }
                H3(
                    TypeTitleStyle
                        .toModifier()
                        .margin(0.px)
                        .toAttrs(),
                ) {
                    Text(post.title)
                }
                P(Modifier.margin(0.px).toAttrs()) {
                    Text(post.description)
                }
            }
        }
    }
}
