package ru.alfacampus.homeworkproject.featureCharacters.data.dto.character
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    @SerialName("id")
    val id: Int,
    @SerialName("path")
    val path: String,
    @SerialName("extension")
    val extension: String
) : java.io.Serializable
