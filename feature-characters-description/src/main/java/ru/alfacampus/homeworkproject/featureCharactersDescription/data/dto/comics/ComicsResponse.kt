package ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ComicsResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("status")
    val status: String,
    @SerialName("copyright")
    val copyright: String,
    @SerialName("attributionText")
    val attributionText: String,
    @SerialName("attributionHTML")
    val attributionHTML: String,
    @SerialName("etag")
    val etag: String,
    @SerialName("data")
    val data: Data
)
