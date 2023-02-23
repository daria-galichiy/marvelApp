package ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Series(
    @SerialName("resourceURI")
    val resourceURI: String,
    @SerialName("name")
    val name: String
)
