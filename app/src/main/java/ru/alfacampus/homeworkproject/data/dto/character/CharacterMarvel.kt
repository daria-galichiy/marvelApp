package ru.alfacampus.homeworkproject.data.dto.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.alfacampus.homeworkproject.data.db.converters.ListUrlConverters
import ru.alfacampus.homeworkproject.data.db.converters.ThumbnailConverters

@Entity("characters")
@TypeConverters(ThumbnailConverters::class, ListUrlConverters::class)
@Serializable
data class CharacterMarvel(
    @PrimaryKey
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("modified")
    val modified: String,
    @SerialName("thumbnail")
    val thumbnail: Thumbnail,
    @SerialName("resourceURI")
    val resourceURI: String,
//    Add these fields if it's necessary to process additional character's data
//    @SerialName("comics")
//    val comics: Comics,
//    @SerialName("series")
//    val series: Series,
//    @SerialName("stories")
//    val stories: Stories,
//    @SerialName("events")
//    val events: Events,
    @SerialName("urls")
    val urls: List<Url>
) : java.io.Serializable
