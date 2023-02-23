package ru.alfacampus.homeworkproject.coreData.data.entities.character

data class DataEntity (
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterMarvelEntity>
)
