package ru.alfacampus.homeworkproject.featureCharacters.data.dto.character
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemXXX(
    @SerialName("resourceURI")
    val resourceURI: String,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String
)
