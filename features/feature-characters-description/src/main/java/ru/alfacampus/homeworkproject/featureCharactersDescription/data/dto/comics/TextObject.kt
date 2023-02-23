package ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class TextObject(
    @SerialName("type")
    val type: String,
    @SerialName("language")
    val language: String,
    @SerialName("text")
    val text: String
)
