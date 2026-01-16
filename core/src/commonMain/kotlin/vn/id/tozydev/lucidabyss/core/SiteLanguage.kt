package vn.id.tozydev.lucidabyss.core

enum class SiteLanguage(
    val code: String,
    val label: String,
) {
    Vi("vi", "Tiếng Việt"),
    En("en", "English"),
    ;

    companion object {
        val Default = Vi

        fun fromCode(code: String) = entries.firstOrNull { it.code == code } ?: Default
    }
}
