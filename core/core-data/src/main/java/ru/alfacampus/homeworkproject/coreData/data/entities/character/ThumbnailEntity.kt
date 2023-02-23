package ru.alfacampus.homeworkproject.coreData.data.entities.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ThumbnailEntity(
    val id: Int,
    val path: String,
    val extension: String
): Parcelable
