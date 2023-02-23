package ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Url(
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String
)
