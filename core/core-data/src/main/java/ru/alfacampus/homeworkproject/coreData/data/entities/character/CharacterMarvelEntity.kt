package ru.alfacampus.homeworkproject.coreData.data.entities.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterMarvelEntity(
    val id: Int,
    val name: String,
    val description: String? = null,
    val modified: String,
    val thumbnail: ThumbnailEntity,
    val resourceURI: String,
    val urls: List<UrlEntity>
): Parcelable
