package ru.alfacampus.featureCreatedCharacters.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CreatedCharacterEntity
import ru.alfacampus.homeworkproject.coreDb.db.dao.CreatedCharactersDao
import ru.alfacampus.homeworkproject.coreDb.db.mappers.CreatedCharacterDbMapper
import javax.inject.Inject


class CreatedCharactersRepositoryImpl @Inject constructor(
    private val createdCharactersDao: CreatedCharactersDao,
    private val createdCharacterDbMapper: CreatedCharacterDbMapper
) : CreatedCharactersRepository {

    override fun getCreatedCharacters(): Flow<List<CreatedCharacterEntity>> {
        return createdCharactersDao.getCreatedCharacters().map { createdCharactersList ->
            createdCharacterDbMapper.fromEntityList(createdCharactersList)
        }
    }

    override suspend fun getCreatedCharacterById(createdCharacterId: Int): CreatedCharacterEntity {
        val createdCharacter = createdCharactersDao.getCreatedCharacterById(createdCharacterId)
        return createdCharacterDbMapper.mapFromEntity(createdCharacter)
    }

    override suspend fun createCharacter(character: CreatedCharacterEntity) {
        val createdCharacterDbEntity = createdCharacterDbMapper.mapToEntity(character)
        createdCharactersDao.insertCreatedCharacter(createdCharacterDbEntity)
    }

    override suspend fun deleteCreatedCharacter(character: CreatedCharacterEntity) {
        val createdCharacterDbEntity = createdCharacterDbMapper.mapToEntity(character)
        createdCharactersDao.deleteCreatedCharacter(createdCharacterDbEntity)
    }

    override fun deleteCreatedCharactersFromDb() {
        createdCharactersDao.deleteCreatedCharacters()
    }
}
