package ru.alfacampus.homeworkproject.featureCharacters.data.repository

import kotlinx.coroutines.flow.Flow
import ru.alfacampus.homeworkproject.coreNetwork.utils.Resource
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharactersResponseEntity

interface CharactersRepository {
    suspend fun getCharacters(offset: Int, limit: Int): Resource<CharactersResponseEntity>
    suspend fun searchCharacters(nameStartsWith: String, limit: Int): Resource<CharactersResponseEntity>
    suspend fun addCharacterToFavorites(character: CharacterMarvelEntity)
}
