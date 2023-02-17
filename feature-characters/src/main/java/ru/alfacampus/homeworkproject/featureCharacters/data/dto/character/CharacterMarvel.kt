package ru.alfacampus.homeworkproject.featureCharacters.data.dto.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterMarvel(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String? = null,
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
