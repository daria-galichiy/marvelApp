package ru.alfacampus.homeworkproject.coreData.data.entities.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreatedCharacterEntity (
    val id: Int? = null,
    val name: String,
    val description: String,
    val thumbnailUri: String
): Parcelable
