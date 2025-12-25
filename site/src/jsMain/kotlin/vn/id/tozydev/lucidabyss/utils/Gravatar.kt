package vn.id.tozydev.lucidabyss.utils

private const val GRAVATAR_BASE_URL = "https://www.gravatar.com/avatar/"

fun getGravatarUrl(
    emailHash: String,
    size: Int = 128,
    extension: String = "webp",
): String = "$GRAVATAR_BASE_URL$emailHash.$extension?s=$size&d=identicon"
