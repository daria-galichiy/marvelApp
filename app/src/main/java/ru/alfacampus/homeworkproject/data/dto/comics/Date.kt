package ru.alfacampus.homeworkproject.data.dto.comics

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Date(
    @SerialName("type")
    val type: String,
    @SerialName("date")
    val date: String
)
