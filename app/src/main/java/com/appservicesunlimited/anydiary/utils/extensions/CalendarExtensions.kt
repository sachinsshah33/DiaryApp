package com.appservicesunlimited.anydiary.utils.extensions

import com.appservicesunlimited.anydiary.utils.helpers.DateTimeHelper
import java.util.*

fun Calendar.getBeginningOfDay(): Calendar {
    this.set(Calendar.HOUR_OF_DAY, 0)
    this.set(Calendar.MINUTE, 0)
    this.set(Calendar.SECOND, 0)
    this.set(Calendar.MILLISECOND, 0)
    return this
}

fun Calendar.getEndOfDay(): Calendar {
    this.set(Calendar.HOUR_OF_DAY, 23)
    this.set(Calendar.MINUTE, 59)
    this.set(Calendar.SECOND, 59)
    this.set(Calendar.MILLISECOND, 999)
    return this
}

fun Calendar.changeDays(daysToChangeBy: Int): Calendar {
    this.add(Calendar.DATE, daysToChangeBy)
    return this
}

fun Calendar.changeHours(hoursToChangeBy: Int): Calendar {
    this.add(Calendar.HOUR, hoursToChangeBy)
    return this
}

fun Calendar.isToday(): Boolean {
    return this.get(Calendar.YEAR) == Calendar.getInstance()
        .get(Calendar.YEAR) && this.get(Calendar.DAY_OF_YEAR) == Calendar.getInstance()
        .get(Calendar.DAY_OF_YEAR)
}


fun Calendar.toDateTimeStamp(): String {
    return DateTimeHelper.getDateTimeStamp(calendar = this)
}

fun Calendar.toDateStamp(): String {
    return DateTimeHelper.getDateStamp(calendar = this)
}

fun Calendar.toTimeStamp(): String {
    return DateTimeHelper.getTimeStamp(calendar = this)
}

fun Calendar.add(
    milliseconds: Int = 0,
    seconds: Int = 0,
    minutes: Int = 0,
    hours: Int = 0,
    days: Int = 0,
    months: Int = 0,
    years: Int = 0
): Calendar {
    this.add(Calendar.MILLISECOND, milliseconds)
    this.add(Calendar.SECOND, seconds)
    this.add(Calendar.MINUTE, minutes)
    this.add(Calendar.HOUR, hours)
    this.add(Calendar.DAY_OF_YEAR, days)
    this.add(Calendar.MONTH, months)
    this.add(Calendar.YEAR, years)
    return this
}