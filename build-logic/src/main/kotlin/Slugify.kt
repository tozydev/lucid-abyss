import java.text.Normalizer

private val nonSpacingMark = Regex("\\p{Mn}")
private val nonLatinWords = Regex("[^a-z0-9]+")

fun slugify(str: String): String =
    Normalizer
        .normalize(str, Normalizer.Form.NFD)
        .lowercase()
        .replace(nonSpacingMark, "")
        .replace(nonLatinWords, "-")
        .replace(Regex("-{2,}"), "-")
        .removePrefix("-")
        .removeSuffix("-")
