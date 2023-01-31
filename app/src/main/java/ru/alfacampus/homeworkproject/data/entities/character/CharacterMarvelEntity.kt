package ru.alfacampus.homeworkproject.data.entities.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.alfacampus.homeworkproject.data.db.converters.ListUrlConverters
import ru.alfacampus.homeworkproject.data.db.converters.ThumbnailConverters
import ru.alfacampus.homeworkproject.data.dto.character.Thumbnail
import ru.alfacampus.homeworkproject.data.dto.character.Url

@Entity("characters")
@TypeConverters(ThumbnailConverters::class, ListUrlConverters::class)
data class CharacterMarvelEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
    val urls: List<Url>
)
