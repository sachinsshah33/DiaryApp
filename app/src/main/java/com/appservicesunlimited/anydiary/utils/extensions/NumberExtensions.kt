package com.appservicesunlimited.anydiary.utils.extensions

import android.graphics.Color
import com.ibm.icu.text.RuleBasedNumberFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


fun Number.toOrdinal() = RuleBasedNumberFormat(
    Locale(Locale.getDefault().language), RuleBasedNumberFormat.ORDINAL
).format(this) ?: this.toString()

fun Number.withCommas() = NumberFormat.getNumberInstance(
    Locale(Locale.getDefault().language)
).format(this) ?: this.toString()

fun Number.toMonth(short: Boolean = false): String {//MONTHS START FROM 0=January
    return SimpleDateFormat(
        if (short) "LLL" else "LLLL",
        Locale(Locale.getDefault().language)
    ).format(Calendar.getInstance().apply { set(Calendar.MONTH, this@toMonth.toInt()) }.time)
}

fun Number.toDay(short: Boolean = false): String {//DAYS START FROM 1=Sunday
    return SimpleDateFormat(
        if (short) "EEE" else "EEEE",
        Locale(Locale.getDefault().language)
    ).format(Calendar.getInstance().apply { set(Calendar.DAY_OF_WEEK, this@toDay.toInt()) }.time)
}

fun Number.toHours(isSeconds: Boolean = false) =
    (if (isSeconds) TimeUnit.SECONDS else TimeUnit.MILLISECONDS).toHours(this.toLong()).toInt()

fun Number.toMinutes(isSeconds: Boolean = false) =
    (if (isSeconds) TimeUnit.SECONDS else TimeUnit.MILLISECONDS).toMinutes(this.toLong()).toInt()

fun Number.toHoursMinutesSeconds() =
    RuleBasedNumberFormat(Locale.UK, RuleBasedNumberFormat.DURATION).format(this)
//fun Number.toHoursMinutes() = RuleBasedNumberFormat(Locale.UK, RuleBasedNumberFormat.DURATION).format(this)


fun Number.toHoursMinutes(): String {
    val hours = this.toHours(true).withCommas()
    val mins = String.format("%02d", this.toMinutes(true) % 60)
    return "$hours:$mins"
}

fun Number.toPercentageOutOf1(total: Number): Float {
    val number = if (this is Float) (total as Float) else this.toFloat()
    val outOff = if (total is Float) total else total.toFloat()
    return number / outOff
}

fun Int.removeAlpha(): Int {
    return Color.rgb(Color.red(this), Color.green(this), Color.blue(this));
}

fun Number.random(): Int {
    return (0..this.toInt()).random()
}