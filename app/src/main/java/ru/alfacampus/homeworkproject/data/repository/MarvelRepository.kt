package ru.alfacampus.homeworkproject.data.repository

import ru.alfacampus.homeworkproject.data.service.CharactersApi
import javax.inject.Inject

class MarvelRepository @Inject constructor(private val charactersApi: CharactersApi){

    suspend fun getCharacters(offset: Int, limit: Int) =
        charactersApi.getCharacters(offset, limit)

    suspend fun searchCharacters(nameStartsWith: String, limit: Int) =
        charactersApi.getCharactersByNameStartsWith(nameStartsWith, limit)

    suspend fun searchComicsByCharacterId(characterId: Int, limit: Int) =
        charactersApi.getComicsByCharacterId(characterId, limit)
}
