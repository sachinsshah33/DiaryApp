package com.appservicesunlimited.anydiary.utils.helpers

import com.appservicesunlimited.anydiary.utils.extensions.getBeginningOfDay
import com.appservicesunlimited.anydiary.utils.extensions.getEndOfDay
import com.appservicesunlimited.anydiary.utils.extensions.removeMultipleBlankSpace
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object DateTimeHelper {

    fun getCalendarFormat(calendar: Calendar? = null, date: Date? = null): Calendar {
        val calendarToReturn = calendar ?: Calendar.getInstance()
        if (date != null) {
            calendarToReturn.time = date
        }
        return calendarToReturn
    }

    fun getYearStamp(calendar: Calendar? = null, date: Date? = null): String {
        return DecimalFormat("0000").format(getCalendarFormat(calendar, date).get(Calendar.YEAR))
    }

    fun getMonthStamp(calendar: Calendar? = null, date: Date? = null): String {
        return DecimalFormat("00").format(getCalendarFormat(calendar, date).get(Calendar.MONTH) + 1)
    }

    fun getDayOfMonthStamp(calendar: Calendar? = null, date: Date? = null): String {
        return DecimalFormat("00").format(
            getCalendarFormat(
                calendar,
                date
            ).get(Calendar.DAY_OF_MONTH)
        )
    }

    fun getHourStamp(calendar: Calendar? = null, date: Date? = null): String {
        return DecimalFormat("00").format(
            getCalendarFormat(
                calendar,
                date
            ).get(Calendar.HOUR_OF_DAY)
        )
    }

    fun getMinuteStamp(calendar: Calendar? = null, date: Date? = null): String {
        return DecimalFormat("00").format(getCalendarFormat(calendar, date).get(Calendar.MINUTE))
    }

    fun getSecondStamp(calendar: Calendar? = null, date: Date? = null): String {
        return DecimalFormat("00").format(getCalendarFormat(calendar, date).get(Calendar.SECOND))
    }

    fun getMillisecondStamp(calendar: Calendar? = null, date: Date? = null): String {
        return DecimalFormat("000").format(
            getCalendarFormat(
                calendar,
                date
            ).get(Calendar.MILLISECOND)
        )
    }

    fun getTimeStamp(calendar: Calendar? = null, date: Date? = null): String {
        val calendarToTarget = getCalendarFormat(calendar, date)
        return "${getHourStamp(calendarToTarget)}:${getMinuteStamp(calendarToTarget)}:${
            getSecondStamp(
                calendarToTarget
            )
        }.${
            getMillisecondStamp(
                calendarToTarget
            )
        }"
    }

    fun getDateStamp(calendar: Calendar? = null, date: Date? = null): String {
        val calendarToTarget = getCalendarFormat(calendar, date)
        return "${getYearStamp(calendarToTarget)}/${getMonthStamp(calendarToTarget)}/${
            getDayOfMonthStamp(
                calendarToTarget
            )
        }"
    }

    fun getDateTimeStamp(calendar: Calendar? = null, date: Date? = null): String {
        val calendarToTarget = getCalendarFormat(calendar, date)
        return "${getDateStamp(calendarToTarget)} ${getTimeStamp(calendarToTarget)}"
    }

    fun getDateTimeAsLongNumberString(calendar: Calendar? = null, date: Date? = null): String {
        val calendarToTarget = getCalendarFormat(calendar, date)
        return "${getYearStamp(calendarToTarget)}${getMonthStamp(calendarToTarget)}${
            getDayOfMonthStamp(
                calendarToTarget
            )
        }${getHourStamp(calendarToTarget)}${getMinuteStamp(calendarToTarget)}${
            getSecondStamp(
                calendarToTarget
            )
        }${getMillisecondStamp(calendarToTarget)}"
    }

    fun areTwoDatesTheSameDay(
        calendarForFirst: Calendar? = null,
        dateForFirst: Date? = null,
        calendarForSecond: Calendar? = null,
        dateForSecond: Date? = null
    ): Boolean {
        val calendarToTargetForFirst = getCalendarFormat(calendarForFirst, dateForFirst)
        val calendarToTargetForSecond = getCalendarFormat(calendarForSecond, dateForSecond)
        return calendarToTargetForFirst.get(Calendar.DAY_OF_YEAR) == calendarToTargetForSecond.get(
            Calendar.DAY_OF_YEAR
        ) && calendarToTargetForFirst.get(Calendar.YEAR) == calendarToTargetForSecond.get(Calendar.YEAR)
    }

    fun getBeginningOfDayAsCalendar(calendar: Calendar? = null, date: Date? = null): Calendar {
        return getCalendarFormat(calendar, date).getBeginningOfDay()
    }

    fun getEndOfDayAsCalendar(calendar: Calendar? = null, date: Date? = null): Calendar {
        return getCalendarFormat(calendar, date).getEndOfDay()
    }


    fun getToDateStampFile(calendar: Calendar? = null, date: Date? = null): String {
        val calendarToTarget = getCalendarFormat(calendar, date)
        return "${getYearStamp(calendarToTarget)}_${getMonthStamp(calendarToTarget)}_${
            getDayOfMonthStamp(
                calendarToTarget
            )
        }_${getHourStamp(calendarToTarget)}_${getMinuteStamp(calendarToTarget)}_${
            getSecondStamp(
                calendarToTarget
            )
        }"
    }


    fun getToDateStamp(calendar: Calendar? = null, date: Date? = null): String {
        val calendarToTarget = getCalendarFormat(calendar, date)
        return "${getDayOfMonthStamp(calendarToTarget)}/${getMonthStamp(calendarToTarget)}/${
            getYearStamp(
                calendarToTarget
            )
        }"
    }

    fun getToMonthStamp(calendar: Calendar? = null, date: Date? = null): String {
        val calendarToTarget = getCalendarFormat(calendar, date)
        return "${getMonthStamp(calendarToTarget)}/${getYearStamp(calendarToTarget)}"
    }

    fun getToYearStamp(calendar: Calendar? = null, date: Date? = null): String {
        val calendarToTarget = getCalendarFormat(calendar, date)
        return getYearStamp(calendarToTarget)
    }


    fun getDateFieldOrder(removeCharacters: Boolean = false): String {
        val fmt = (DateFormat.getDateInstance(
            DateFormat.SHORT,
            Locale.getDefault()
        ) as SimpleDateFormat).toPattern().toUpperCase()
        if (removeCharacters) {
            return fmt.replace("[^a-zA-Z]".toRegex(), " ").removeMultipleBlankSpace().trim()
        }
        return fmt.removeMultipleBlankSpace().trim()
    }
}