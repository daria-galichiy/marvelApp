package ru.alfacampus.homeworkproject.coreData.data.entities.character

data class CharactersResponseEntity (
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: DataEntity
)
