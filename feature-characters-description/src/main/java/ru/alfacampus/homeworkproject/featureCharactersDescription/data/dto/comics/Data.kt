package ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Data(
    @SerialName("offset")
    val offset: Int,
    @SerialName("limit")
    val limit: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("count")
    val count: Int,
    @SerialName("results")
    val results: List<Result>
)
