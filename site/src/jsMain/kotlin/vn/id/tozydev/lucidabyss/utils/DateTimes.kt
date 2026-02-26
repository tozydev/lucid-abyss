package vn.id.tozydev.lucidabyss.utils

import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.MonthNames
import kotlin.time.Instant

private val dateFormat =
    DateTimeComponents.Format {
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        chars(" ")
        day()
        chars(", ")
        year()
    }

fun Instant.formatDate(): String = this.format(dateFormat)
