package ru.alfacampus.featureCreatedCharacters.data.repository

import kotlinx.coroutines.flow.Flow
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CreatedCharacterEntity


interface CreatedCharactersRepository {
    fun getCreatedCharacters(): Flow<List<CreatedCharacterEntity>>
    suspend fun getCreatedCharacterById(createdCharacterId: Int): CreatedCharacterEntity
    suspend fun createCharacter(character: CreatedCharacterEntity)
    suspend fun deleteCreatedCharacter(character: CreatedCharacterEntity)
    fun deleteCreatedCharactersFromDb()
}
