package eu.ciambella.dailytest.present.common.formatter

import eu.ciambella.dailytest.present.resource.StringResources
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import kotlin.math.max

class DateFormatter(
    private val stringResources: StringResources,
) {

    companion object {
        private const val FRENCH_TIMEZONE = "Europe/Paris"
        private const val FULL_DATE_FORMAT = "d MMMM yyyy"
        private const val DAY_IN_HOUR = 24L
        private const val HOUR_IN_MINUTES = 60L
        private const val SECOND_IN_MILLIS: Long = 1000L
        private const val MINUTE_IN_MILLIS: Long = SECOND_IN_MILLIS * 60
    }

    private val applicationTimeZone: TimeZone by lazy {
        TimeZone.getTimeZone(FRENCH_TIMEZONE)
    }

    fun formatWhenDate(
        startTimeMs: Long?,
        currentTimeMs: Long,
    ): String? {
        if (startTimeMs == null) {
            return null
        }
        val timePassed = currentTimeMs - startTimeMs
        if (timePassed < 0) {
            return null
        }
        val minutes = max(timePassed / MINUTE_IN_MILLIS, 1)
        if (minutes < HOUR_IN_MINUTES) {
            return stringResources.whenDateMinutes(minutes.toString())
        }
        val hours = minutes / HOUR_IN_MINUTES
        if (hours < DAY_IN_HOUR) {
            return stringResources.whenDateHours(hours.toString())
        }
        val formatted = SimpleDateFormat(FULL_DATE_FORMAT, Locale.FRANCE).apply {
            timeZone = applicationTimeZone
        }.format(startTimeMs)
        return formatted
    }

}
