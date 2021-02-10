package com.appservicesunlimited.anydiary.utils.extensions

import com.appservicesunlimited.anydiary.utils.helpers.DateTimeHelper
import org.apache.commons.lang3.time.DateUtils
import java.text.SimpleDateFormat
import java.util.*


fun Date.getBeginningOfDay(): Date {
    var date = this
    val calendar = Calendar.getInstance().apply { time = date }.getBeginningOfDay()
    date = calendar.time
    return date
}

fun Date.getEndOfDay(): Date {
    var date = this
    val calendar = Calendar.getInstance().apply { time = date }.getEndOfDay()
    date = calendar.time
    return date
}

fun Date.toCalander(): Calendar {
    val date = this
    return Calendar.getInstance().apply { time = date }
}

fun Date.isToday(): Boolean {
    val calendar = DateTimeHelper.getCalendarFormat(date = this)
    return calendar.isToday()
}

fun Date.toDateTimeStamp(): String {
    return DateTimeHelper.getDateTimeStamp(date = this)
}

fun Date.toDateStamp(): String {
    return DateTimeHelper.getDateStamp(date = this)
}

fun Date.toTimeStamp(): String {
    return DateTimeHelper.getTimeStamp(date = this)
}

fun Date.getMonthAsString(makeShort: Boolean = false): String {
    return (this.getMonthAsInt() - 1).toMonth(makeShort)
}

fun Date.getMonthAsInt(): Int {
    return this.toCalander().get(Calendar.MONTH) + 1
}

fun Date.getDayOfMonthAsString(): String {
    return this.toCalander().get(Calendar.DAY_OF_MONTH).toOrdinal()
}

fun Date.getYearAsString(makeShort: Boolean? = false): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val year = calendar.get(Calendar.YEAR).toString()

    if (makeShort != null && makeShort) {
        return year.substring(year.length - 2, 2)
    }
    return year
}

fun Date.getDayOfWeekAsString(makeShort: Boolean = false): String {
    return this.toCalander().get(Calendar.DAY_OF_WEEK).toDay(makeShort)
}

fun Date.getDayOfMonthAsInt(): Int {
    return this.toCalander().get(Calendar.DAY_OF_MONTH)
}

fun Date.getAmOrPmAsString(): String {
    return SimpleDateFormat(
        "a",
        Locale(Locale.getDefault().language)
    ).format(this)
}

fun Date.getHourAsString(is12Hour: Boolean? = false): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    if (is12Hour != null && is12Hour) {
        return calendar.get(Calendar.HOUR).toString()
    }
    return String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY))
}

fun Date.getMinuteAsString(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return String.format("%02d", calendar.get(Calendar.MINUTE))
}


fun Date.getDateInReadableString(): String {
    return this.getDayOfWeekAsString(true) + " " + this.getDayOfMonthAsString() + " " + this.getMonthAsString(
        true
    ) + " " + this.getYearAsString()
}


fun Date.getTimeInReadibleString(): String {
    return this.getHourAsString() + ":" + this.getMinuteAsString() + this.getAmOrPmAsString()
}

fun Date.getTimeInReadibleString24(): String {
    return this.getHourAsString() + ":" + this.getMinuteAsString()
}

fun Date.removeTime(): Date {
    return DateUtils.truncate(this, Calendar.DATE)
}


fun Date.getToDateStamp(): String {
    return DateTimeHelper.getToDateStamp(date = this)
}

fun Date.getToMonthStamp(): String {
    return DateTimeHelper.getToMonthStamp(date = this)
}

fun Date.getToYearStamp(): String {
    return DateTimeHelper.getToYearStamp(date = this)
}


fun Date.fullDayMonthYearUltimate(
    dayOfWeekShort: Boolean? = false,
    dayAsOrdinal: Boolean? = false,
    monthShort: Boolean? = false,
    monthAsInt: Boolean? = false,
    yearShort: Boolean? = false
): String {
    //Monday 15th August 2020

    val dayOfWeek = if (dayOfWeekShort != null) this.getDayOfWeekAsString(dayOfWeekShort) else ""
    val dayOfMonthAsOrdinal =
        if (dayAsOrdinal == null) "" else (if (dayAsOrdinal) this.getDayOfMonthAsInt()
            .toOrdinal() else this.getDayOfMonthAsInt().toString())
    val month =
        if (monthShort == null && monthAsInt == null) "" else (if (monthAsInt == true) this.getMonthAsInt()
            .toString() else this.getMonthAsString(monthShort!!))
    val year = if (yearShort == null) "" else this.getYearAsString(yearShort)

    var pattern = DateTimeHelper.getDateFieldOrder(!(dayAsOrdinal == false && monthAsInt == true))
    pattern = pattern.replace("DD", "D")
    pattern = pattern.replace("D", dayOfMonthAsOrdinal)

    pattern = pattern.replace("MM", "M")
    pattern = pattern.replace("M", month)


    pattern = pattern.replace("YY", "Y")
    pattern = pattern.replace("Y", year)


    val toReturn = if (dayOfWeekShort != null) {
        if (pattern.trim().isEmpty()) {
            dayOfWeek
        } else {
            "$dayOfWeek, $pattern"
        }
    } else {
        pattern
    }

    return toReturn.removeMultipleBlankSpace().trim()
}

