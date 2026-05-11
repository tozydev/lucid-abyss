import java.text.Normalizer

private val nonSpacingMark = Regex("\\p{Mn}")
private val nonLatinWords = Regex("[^a-z0-9]+")
private val multipleHyphens = Regex("-{2,}")

fun slugify(str: String): String =
    Normalizer
        .normalize(str, Normalizer.Form.NFD)
        .lowercase()
        .replace(nonSpacingMark, "")
        .replace(nonLatinWords, "-")
        .replace(multipleHyphens, "-")
        .removePrefix("-")
        .removeSuffix("-")
