package ru.alfacampus.homeworkproject.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterMarvel(
    val id: Long,
    val name: String,
    val description: String? = "",
    val thumbnail: String
)
