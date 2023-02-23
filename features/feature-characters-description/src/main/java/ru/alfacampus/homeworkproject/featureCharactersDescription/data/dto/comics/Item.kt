package ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Item(
    @SerialName("resourceURI")
    val resourceURI: String,
    @SerialName("name")
    val name: String
)