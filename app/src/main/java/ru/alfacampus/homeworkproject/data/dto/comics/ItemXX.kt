package ru.alfacampus.homeworkproject.data.dto.comics

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemXX(
    @SerialName("resourceURI")
    val resourceURI: String,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String
)
