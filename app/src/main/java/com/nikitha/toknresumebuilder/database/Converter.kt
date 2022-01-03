package com.nikitha.toknresumebuilder.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.nikitha.toknresumebuilder.model.Designation

class Converter {

    @TypeConverter
    fun listToJsonString(value: List<Designation>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<Designation>::class.java).toList()
}