package ru.alfacampus.homeworkproject.featureCharacters.data.dto.character
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Series(
    @SerialName("available")
    val available: Int,
    @SerialName("collectionURI")
    val collectionURI: String,
    @SerialName("items")
    val items: List<Item>,
    @SerialName("returned")
    val returned: Int
)