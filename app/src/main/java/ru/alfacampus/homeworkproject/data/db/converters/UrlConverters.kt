package ru.alfacampus.homeworkproject.data.db.converters

import androidx.room.TypeConverter
import ru.alfacampus.homeworkproject.data.dto.character.Url

class UrlConverters {
    @TypeConverter
    fun fromUrl(url: Url): String {
        return url.type + "," + url.url
    }

    @TypeConverter
    fun toUrl(string: String): Url {
        val data = string.split(",")
        return Url(data[0], data[1])
    }
}
