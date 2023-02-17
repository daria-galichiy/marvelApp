package ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Price(
    @SerialName("type")
    val type: String,
    @SerialName("price")
    val price: Double
)
