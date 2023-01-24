package ru.alfacampus.homeworkproject.data.repository

import ru.alfacampus.homeworkproject.data.db.CharactersDao
import ru.alfacampus.homeworkproject.data.dto.character.CharacterMarvel
import ru.alfacampus.homeworkproject.data.service.CharactersApi
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val charactersApi: CharactersApi,
    private val charactersDao: CharactersDao
) {
    suspend fun getCharacters(offset: Int, limit: Int) =
        charactersApi.getCharacters(offset, limit)

    suspend fun searchCharacters(nameStartsWith: String, limit: Int) =
        charactersApi.getCharactersByNameStartsWith(nameStartsWith, limit)

    suspend fun searchComicsByCharacterId(characterId: Int, limit: Int) =
        charactersApi.getComicsByCharacterId(characterId, limit)


    fun getFavoriteCharacters() = charactersDao.getCharacters()

    suspend fun addCharacterToFavorites(character: CharacterMarvel) =
        charactersDao.insert(character)

    suspend fun deleteCharacterFromFavorites(character: CharacterMarvel) =
        charactersDao.delete(character)


    fun deleteCharactersFromDb() = charactersDao.deleteCharacters()
}
