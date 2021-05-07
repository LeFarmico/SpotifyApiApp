package com.lefarmico.flatstacktesttask.utils

import androidx.room.TypeConverter

class ListOfStringsTypeConverter {

    @TypeConverter
    fun fromListToString(list: List<String>): String =
        list.joinToString(",")

    @TypeConverter
    fun fromStringToList(string: String): List<String> =
        string.split(",").toList()
}