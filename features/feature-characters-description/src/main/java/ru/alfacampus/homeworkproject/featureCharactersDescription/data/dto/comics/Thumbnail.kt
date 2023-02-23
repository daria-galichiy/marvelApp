package ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Thumbnail(
    @SerialName("path")
    val path: String,
    @SerialName("extension")
    val extension: String
)
