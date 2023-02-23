package ru.alfacampus.homeworkproject.featureCharacters.data.dto.character
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("status")
    val status: String,
    @SerialName("copyright")
    val copyright: String,
    @SerialName("attributionText")
    val attributionText: String,
    @SerialName("attributionHTML")
    val attributionHTML: String,
    @SerialName("etag")
    val etag: String,
    @SerialName("data")
    val data: Data
)
