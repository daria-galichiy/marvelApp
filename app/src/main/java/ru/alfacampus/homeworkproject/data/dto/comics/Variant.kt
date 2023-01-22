package ru.alfacampus.homeworkproject.data.dto.comics

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Variant(
    @SerialName("name")
    val name: String,
    @SerialName("resourceURI")
    val resourceURI: String
)
