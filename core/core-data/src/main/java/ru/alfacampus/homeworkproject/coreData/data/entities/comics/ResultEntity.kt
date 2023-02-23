package ru.alfacampus.homeworkproject.coreData.data.entities.comics

data class ResultEntity(
    val id: Int,
    val digitalId: Int,
    val title: String,
    val issueNumber: String,
    val variantDescription: String? = null,
    val description: String? = null,
    val modified: String,
    val isbn: String,
    val upc: String,
    val diamondCode: String,
    val ean: String,
    val issn: String,
    val format: String,
    val pageCount: Int,
    val thumbnail: ThumbnailEntity,
)
