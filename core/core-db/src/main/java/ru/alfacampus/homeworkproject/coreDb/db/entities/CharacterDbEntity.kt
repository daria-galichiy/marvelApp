package ru.alfacampus.homeworkproject.coreDb.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.alfacampus.homeworkproject.coreData.data.entities.character.ThumbnailEntity
import ru.alfacampus.homeworkproject.coreData.data.entities.character.UrlEntity
import ru.alfacampus.homeworkproject.coreDb.db.converters.ListUrlConverter
import ru.alfacampus.homeworkproject.coreDb.db.converters.ThumbnailConverter


@Entity("characters")
@TypeConverters(ThumbnailConverter::class, ListUrlConverter::class)
data class CharacterDbEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String? = null,
    val modified: String,
    val thumbnail: ThumbnailEntity,
    val resourceURI: String,
    val urls: List<UrlEntity>
)
