package ru.alfacampus.homeworkproject.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.alfacampus.homeworkproject.data.dto.character.Url
import java.lang.reflect.Type


class ListUrlConverters {
    @TypeConverter
    fun fromUrlsList(urlsList: List<Url?>?): String? {
        if (urlsList == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<Url?>?>() {}.type
        return gson.toJson(urlsList, type)
    }

    @TypeConverter
    fun toUrlsList(urlString: String?): List<Url>? {
        if (urlString == null) {
            return null
        }
        val gson = Gson()
        val type = object :
            TypeToken<List<Url?>?>() {}.type
        return gson.fromJson<List<Url>>(
            urlString,
            type
        )
    }
}
