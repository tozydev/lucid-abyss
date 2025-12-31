package vn.id.tozydev.lucidabyss.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.components.widgets.ColumnIslandVariant
import vn.id.tozydev.lucidabyss.components.widgets.Island

@Composable
fun Quote(modifier: Modifier = Modifier) {
    Island(
        modifier = modifier,
        variance = ColumnIslandVariant,
    ) {
        P { Text("“Code is like humor. When you have to explain it, it’s bad.”") }
        SpanText("– Cory House", Modifier.alignSelf(AlignSelf.FlexEnd))
    }
}
