package ru.alfacampus.homeworkproject.data.repository

import androidx.lifecycle.LiveData
import ru.alfacampus.homeworkproject.data.dto.CharacterMarvel

interface CharactersRepository {
    val data: LiveData<List<CharacterMarvel>>
    fun delete(characterId: Long)
    fun save(characterMarvel: CharacterMarvel)

    companion object {
        const val NEW_CHARACTER_ID = 0L
    }
}
