package ru.alfacampus.homeworkproject.featureCharacters.data.dto.character
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("offset")
    val offset: Int,
    @SerialName("limit")
    val limit: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("count")
    val count: Int,
    @SerialName("results")
    val results: List<CharacterMarvel>
)
