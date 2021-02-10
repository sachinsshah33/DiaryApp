package com.appservicesunlimited.anydiary.data


import androidx.room.TypeConverter
import com.appservicesunlimited.anydiary.utils.helpers.Constants.OptionFieldType
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?) = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?) = date?.time


    @TypeConverter
    fun fromOptionFieldType(value: OptionFieldType?) = value?.let { value.value }

    @TypeConverter
    fun toOptionFieldType(value: Int?) = value?.let { OptionFieldType.fromInt(it) }
}