package ru.alfacampus.homeworkproject.featureFavoriteCharacters.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.coreDb.db.dao.CharactersDao
import ru.alfacampus.homeworkproject.coreDb.db.mappers.CharacterDbMapper
import javax.inject.Inject


class FavoriteCharactersRepositoryImpl @Inject constructor(
    private val charactersDao: CharactersDao,
    private val dbMapper: CharacterDbMapper
): FavoriteCharactersRepository {

    override fun getFavoriteCharacters(): Flow<List<CharacterMarvelEntity>> {
        return charactersDao.getCharacters().map { charactersList ->
            dbMapper.fromEntityList(charactersList)
        }
    }

    override suspend fun addCharacterToFavorites(character: CharacterMarvelEntity) {
        val characterDbEntity = dbMapper.mapToEntity(character)
        charactersDao.insert(characterDbEntity)
    }

    override suspend fun deleteCharacterFromFavorites(character: CharacterMarvelEntity) {
        val characterDbEntity = dbMapper.mapToEntity(character)
        charactersDao.delete(characterDbEntity)
    }

    override fun deleteCharactersFromDb() {
        charactersDao.deleteCharacters()
    }
}
