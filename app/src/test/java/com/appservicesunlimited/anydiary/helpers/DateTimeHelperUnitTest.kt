package com.appservicesunlimited.anydiary.helpers

import com.appservicesunlimited.anydiary.utils.helpers.DateTimeHelper
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*


class DateTimeHelperUnitTest {
    @Test
    fun year_isCorrect() {
        val yearToSet = 2021
        val calendar = Calendar.getInstance().apply {
            set(Calendar.SECOND, 0)
            set(Calendar.MINUTE, 13)
            set(Calendar.HOUR, 7)
            set(Calendar.AM_PM, Calendar.AM)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DAY_OF_MONTH, 9)
            set(Calendar.YEAR, yearToSet)
        }
        val yearAsString = DateTimeHelper.getYearStamp(calendar)
        assertEquals(yearToSet.toString(), yearAsString)
    }

    @Test
    fun twoDatesTheSame_isCorrect() {
        val calendar1 = Calendar.getInstance().apply {
            set(Calendar.SECOND, 0)
            set(Calendar.MINUTE, 13)
            set(Calendar.HOUR, 7)
            set(Calendar.AM_PM, Calendar.AM)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DAY_OF_MONTH, 9)
            set(Calendar.YEAR, 2021)
        }
        val calendar2 = Calendar.getInstance().apply {
            set(Calendar.SECOND, 0)
            set(Calendar.MINUTE, 13)
            set(Calendar.HOUR, 17)
            set(Calendar.AM_PM, Calendar.AM)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DAY_OF_MONTH, 9)
            set(Calendar.YEAR, 2021)
        }
        val areTwoDatesTheSameDay = DateTimeHelper.areTwoDatesTheSameDay(calendarForFirst = calendar1, calendarForSecond = calendar2)
        assertEquals(true, areTwoDatesTheSameDay)
    }

    @Test
    fun twoDatesNotTheSame_isCorrect() {
        val calendar1 = Calendar.getInstance().apply {
            set(Calendar.SECOND, 0)
            set(Calendar.MINUTE, 13)
            set(Calendar.HOUR, 7)
            set(Calendar.AM_PM, Calendar.AM)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DAY_OF_MONTH, 19)
            set(Calendar.YEAR, 2021)
        }
        val calendar2 = Calendar.getInstance().apply {
            set(Calendar.SECOND, 0)
            set(Calendar.MINUTE, 13)
            set(Calendar.HOUR, 17)
            set(Calendar.AM_PM, Calendar.AM)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DAY_OF_MONTH, 9)
            set(Calendar.YEAR, 2021)
        }
        val areTwoDatesTheSameDay = DateTimeHelper.areTwoDatesTheSameDay(calendarForFirst = calendar1, calendarForSecond = calendar2)
        assertEquals(false, areTwoDatesTheSameDay)
    }
}