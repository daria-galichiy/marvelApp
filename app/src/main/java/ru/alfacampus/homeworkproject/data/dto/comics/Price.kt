package ru.alfacampus.homeworkproject.data.dto.comics

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Price(
    @SerialName("type")
    val type: String,
    @SerialName("price")
    val price: Double
)
