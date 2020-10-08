package com.example.madlevel4task2.converters

import androidx.room.TypeConverter
import com.example.madlevel4task2.entities.MatchOption
import com.example.madlevel4task2.entities.MatchOutcome
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun toMatchOption(value: Int) = enumValues<MatchOption>()[value]

    @TypeConverter
    fun fromMatchOption(value: MatchOption) = value.ordinal

    @TypeConverter
    fun toMatchOutcome(value: Int) = enumValues<MatchOutcome>()[value]

    @TypeConverter
    fun fromMatchOutcome(value: MatchOutcome) = value.ordinal
}