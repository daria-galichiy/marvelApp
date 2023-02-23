package ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Date(
    @SerialName("type")
    val type: String,
    @SerialName("date")
    val date: String
)
