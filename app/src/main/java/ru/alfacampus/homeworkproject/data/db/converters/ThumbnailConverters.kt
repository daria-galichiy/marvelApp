package ru.alfacampus.homeworkproject.data.db.converters

import androidx.room.TypeConverter
import ru.alfacampus.homeworkproject.data.dto.character.Thumbnail

class ThumbnailConverters {
    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail): String {
        return thumbnail.id.toString() + "," + thumbnail.path + "," + thumbnail.extension
    }

    @TypeConverter
    fun toThumbnail(string: String): Thumbnail {
        val data = string.split(",")
        return Thumbnail(data[0].toInt(), data[1], data[2])
    }
}
