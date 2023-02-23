package ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Variant(
    @SerialName("name")
    val name: String,
    @SerialName("resourceURI")
    val resourceURI: String
)
