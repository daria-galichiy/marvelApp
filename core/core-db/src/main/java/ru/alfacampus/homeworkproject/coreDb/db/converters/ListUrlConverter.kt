package ru.alfacampus.homeworkproject.coreDb.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.alfacampus.homeworkproject.coreData.data.entities.character.UrlEntity
import java.lang.reflect.Type


class ListUrlConverter {
    @TypeConverter
    fun fromUrlsList(urlsList: List<UrlEntity?>?): String? {
        if (urlsList == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<UrlEntity?>?>() {}.type
        return gson.toJson(urlsList, type)
    }

    @TypeConverter
    fun toUrlsList(urlString: String?): List<UrlEntity>? {
        if (urlString == null) {
            return null
        }
        val gson = Gson()
        val type = object :
            TypeToken<List<UrlEntity?>?>() {}.type
        return gson.fromJson<List<UrlEntity>>(
            urlString,
            type
        )
    }
}
