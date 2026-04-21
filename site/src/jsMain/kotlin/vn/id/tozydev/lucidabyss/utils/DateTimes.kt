package vn.id.tozydev.lucidabyss.utils

import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.toLocalDateTime
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

val Instant.year: Int
    get() = toLocalDateTime(TimeZone.UTC).year
