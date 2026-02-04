package vn.id.tozydev.lucidabyss.utils

import strings.Strings
import strings.StringsEn
import strings.StringsVi
import vn.id.tozydev.lucidabyss.core.SiteLanguage

val SiteLanguage.strings: Strings
    get() =
        when (this) {
            SiteLanguage.En -> StringsEn
            else -> StringsVi
        }
