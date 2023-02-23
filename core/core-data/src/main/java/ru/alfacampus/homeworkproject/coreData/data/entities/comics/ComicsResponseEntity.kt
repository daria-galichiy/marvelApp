package ru.alfacampus.homeworkproject.coreData.data.entities.comics

data class ComicsResponseEntity(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: DataEntity
)
