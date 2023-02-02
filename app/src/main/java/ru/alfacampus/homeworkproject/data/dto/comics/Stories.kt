package ru.alfacampus.homeworkproject.data.dto.comics

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stories(
    @SerialName("available")
    val available: Int,
    @SerialName("collectionURI")
    val collectionURI: String,
    @SerialName("items")
    val items: List<ItemXX>,
    @SerialName("returned")
    val returned: Int
)
