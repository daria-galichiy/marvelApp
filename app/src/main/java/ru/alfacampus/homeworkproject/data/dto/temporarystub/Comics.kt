package ru.alfacampus.homeworkproject.data.dto.temporarystub

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Comics(
    @SerialName("available")
    val available: Int?,
    @SerialName("collectionURI")
    val collectionURI: String?
)
