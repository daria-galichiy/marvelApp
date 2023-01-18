package ru.alfacampus.homeworkproject.data.dto.temporarystub

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    @SerialName("path")
    val path: String,
    @SerialName("extension")
    val extension: String
)
