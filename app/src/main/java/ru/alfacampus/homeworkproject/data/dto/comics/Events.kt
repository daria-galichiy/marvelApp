package ru.alfacampus.homeworkproject.data.dto.comics

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Events(
    @SerialName("available")
    val available: Int,
    @SerialName("collectionURI")
    val collectionURI: String,
    @SerialName("returned")
    val returned: Int
)
