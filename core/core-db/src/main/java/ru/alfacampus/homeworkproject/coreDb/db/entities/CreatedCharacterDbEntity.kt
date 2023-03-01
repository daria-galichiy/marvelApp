package ru.alfacampus.homeworkproject.coreDb.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("created_characters")
data class CreatedCharacterDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val thumbnailUri: String
)
