package ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ItemX(
    @SerialName("resourceURI")
    val resourceURI: String,
    @SerialName("name")
    val name: String,
    @SerialName("role")
    val role: String
)
