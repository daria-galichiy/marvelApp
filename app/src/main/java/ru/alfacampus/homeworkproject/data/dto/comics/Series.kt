package ru.alfacampus.homeworkproject.data.dto.comics

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Series(
    @SerialName("resourceURI")
    val resourceURI: String,
    @SerialName("name")
    val name: String
)
