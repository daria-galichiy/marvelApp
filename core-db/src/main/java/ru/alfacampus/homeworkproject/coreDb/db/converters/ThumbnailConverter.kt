package ru.alfacampus.homeworkproject.coreDb.db.converters

import androidx.room.TypeConverter
import ru.alfacampus.homeworkproject.coreData.data.entities.character.ThumbnailEntity

class ThumbnailConverter {
    @TypeConverter
    fun fromThumbnail(thumbnail: ThumbnailEntity): String {
        return thumbnail.id.toString() + "," + thumbnail.path + "," + thumbnail.extension
    }

    @TypeConverter
    fun toThumbnail(string: String): ThumbnailEntity {
        val data = string.split(",")
        return ThumbnailEntity(data[0].toInt(), data[1], data[2])
    }
}
