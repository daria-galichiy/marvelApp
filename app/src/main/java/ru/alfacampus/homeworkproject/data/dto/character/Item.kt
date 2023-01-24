package ru.alfacampus.homeworkproject.data.dto.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("resourceURI")
    val resourceURI: String,
    @SerialName("name")
    val name: String
)