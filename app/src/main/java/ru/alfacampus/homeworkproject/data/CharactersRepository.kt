package ru.alfacampus.homeworkproject.data

import androidx.lifecycle.LiveData
import ru.alfacampus.homeworkproject.dto.CharacterMarvel

interface CharactersRepository {
    val data: LiveData<List<CharacterMarvel>>
    fun delete(characterId: Long)
    fun save(characterMarvel: CharacterMarvel)

    companion object {
        const val NEW_CHARACTER_ID = 0L
    }
}
