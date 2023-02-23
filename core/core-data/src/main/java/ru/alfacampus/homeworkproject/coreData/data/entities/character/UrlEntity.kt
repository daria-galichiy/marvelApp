package ru.alfacampus.homeworkproject.coreData.data.entities.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UrlEntity(
    val type: String,
    val url: String
): Parcelable
