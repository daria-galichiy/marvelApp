package ru.alfacampus.homeworkproject.featureCharactersDescription.data.repository

import ru.alfacampus.homeworkproject.coreNetwork.utils.Resource
import ru.alfacampus.homeworkproject.coreData.data.entities.comics.ComicsResponseEntity

interface ComicsRepository {
    suspend fun searchComicsByCharacterId(characterId: Int, limit: Int): Resource<ComicsResponseEntity>
}
