package ru.alfacampus.homeworkproject.featureCharacters.data.repository

import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharactersResponseEntity
import ru.alfacampus.homeworkproject.coreDb.db.dao.CharactersDao
import ru.alfacampus.homeworkproject.coreDb.db.mappers.CharacterDbMapper
import ru.alfacampus.homeworkproject.coreNetwork.utils.Resource
import ru.alfacampus.homeworkproject.featureCharacters.data.ds.CharactersService
import ru.alfacampus.homeworkproject.featureCharacters.data.mappers.CharactersResponseMapper
import javax.inject.Inject


class CharactersRepositoryImpl @Inject constructor(
    private val charactersService: CharactersService,
    private val mapper: CharactersResponseMapper,
    private val charactersDao: CharactersDao,
    private val dbMapper: CharacterDbMapper
) : CharactersRepository {

    override suspend fun getCharacters(offset: Int, limit: Int)
            : Resource<CharactersResponseEntity> {
        val charactersResponse = charactersService.getCharacters(offset, limit)
        return if (charactersResponse.isSuccessful)
            Resource.Success(charactersResponse.body()?.let { mapper.mapToEntity(it) })
        else
            Resource.Error(charactersResponse.message())
    }

    override suspend fun searchCharacters(nameStartsWith: String, limit: Int)
            : Resource<CharactersResponseEntity> {
        val charactersResponse =
            charactersService.getCharactersByNameStartsWith(nameStartsWith, limit)
        return if (charactersResponse.isSuccessful)
            Resource.Success(charactersResponse.body()?.let { mapper.mapToEntity(it) })
        else
            Resource.Error(charactersResponse.message())
    }

    override suspend fun addCharacterToFavorites(character: CharacterMarvelEntity) {
        val characterDbEntity = dbMapper.mapToEntity(character)
        charactersDao.insert(characterDbEntity)
    }
}
