package ru.alfacampus.homeworkproject.featureFavoriteCharacters.data.repository

import kotlinx.coroutines.flow.Flow
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity

interface FavoriteCharactersRepository {
    fun getFavoriteCharacters(): Flow<List<CharacterMarvelEntity>>
    suspend fun addCharacterToFavorites(character: CharacterMarvelEntity)
    suspend fun deleteCharacterFromFavorites(character: CharacterMarvelEntity)
    fun deleteCharactersFromDb()
}
