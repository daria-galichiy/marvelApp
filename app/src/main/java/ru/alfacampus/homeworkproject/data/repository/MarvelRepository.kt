package ru.alfacampus.homeworkproject.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import ru.alfacampus.homeworkproject.data.db.CharactersDao
import ru.alfacampus.homeworkproject.data.db.mappers.CharacterMarvelMapper
import ru.alfacampus.homeworkproject.data.dto.character.CharacterMarvel
import ru.alfacampus.homeworkproject.data.dto.character.CharactersResponse
import ru.alfacampus.homeworkproject.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.data.service.CharactersApi
import ru.alfacampus.homeworkproject.utils.Resource
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


    fun getFavoriteCharacters() =
        Transformations.map(charactersDao.getCharacters(),
            fun(initial: List<CharacterMarvelEntity>): List<CharacterMarvel> {
                return initial.map { CharacterMarvelMapper().mapFromEntity(it) }
            })

    suspend fun addCharacterToFavorites(character: CharacterMarvel) {
        val characterMarvelEntity = CharacterMarvelMapper().mapToEntity(character)
        charactersDao.insert(characterMarvelEntity)
    }

    suspend fun deleteCharacterFromFavorites(character: CharacterMarvel) {
        val characterMarvelEntity = CharacterMarvelMapper().mapToEntity(character)
        charactersDao.delete(characterMarvelEntity)
    }

    fun deleteCharactersFromDb() = charactersDao.deleteCharacters()
}
