package vn.id.tozydev.lucidabyss.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class SiteLanguage(
    val code: String,
    val label: String,
) {
    Vi("vi", "Tiếng Việt"),
    En("en", "English"),
    ;

    companion object {
        val Default = Vi

        private val _currentState = MutableStateFlow(Default)
        val currentState: StateFlow<SiteLanguage>
            get() = _currentState.asStateFlow()

        var current: SiteLanguage by _currentState::value

        fun fromCode(code: String) = entries.firstOrNull { it.code == code } ?: Default
    }
}
