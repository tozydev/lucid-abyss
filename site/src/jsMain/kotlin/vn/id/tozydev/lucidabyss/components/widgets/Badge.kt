package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.Anchor
import org.jetbrains.compose.web.dom.*
import vn.id.tozydev.lucidabyss.utils.tw

enum class BadgeVariant {
    Primary,
    Surface,
}

enum class BadgeSize {
    Sm,
    Md,
    Lg,
}

@Composable
fun Badge(
    text: String,
    modifier: Modifier = Modifier,
    href: String? = null,
    variant: BadgeVariant = BadgeVariant.Primary,
    size: BadgeSize = BadgeSize.Md,
) {
    val baseClasses = "rounded uppercase transition-all tracking-widest inline-flex items-center"

    val sizeClasses =
        when (size) {
            BadgeSize.Sm -> "px-2 py-0.5 text-[10px]"
            BadgeSize.Md -> "px-3 py-1.5 text-[10px]"
            BadgeSize.Lg -> "px-5 py-3 text-xs rounded-full"
        }

    val variantClasses =
        when (variant) {
            BadgeVariant.Primary -> {
                "bg-primary-container text-on-primary-container font-mono hover:brightness-110"
            }

            BadgeVariant.Surface -> {
                "bg-surface-container-high text-on-surface-variant font-label hover:bg-primary-container hover:text-on-primary-container"
            }
        }

    val finalClasses = "$baseClasses $sizeClasses $variantClasses"

    if (href != null) {
        Anchor(
            href = href,
            attrs = Modifier.tw(finalClasses).then(modifier).toAttrs(),
        ) {
            Text(text)
        }
    } else {
        Span(Modifier.tw(finalClasses).then(modifier).toAttrs()) {
            Text(text)
        }
    }
}
