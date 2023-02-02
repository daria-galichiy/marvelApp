package ru.alfacampus.homeworkproject.data.dto.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Url(
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String
) : java.io.Serializable
