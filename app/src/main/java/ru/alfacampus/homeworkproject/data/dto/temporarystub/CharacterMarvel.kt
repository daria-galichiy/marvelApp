package ru.alfacampus.homeworkproject.data.dto.temporarystub

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterMarvel(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String? = "",
    @SerialName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerialName("comics")
    val comics: Comics? = null,
    val simpleImage: String? = ""
)
