package ru.alfacampus.homeworkproject.featureCharactersDescription.data.repository

import ru.alfacampus.homeworkproject.coreNetwork.utils.Resource
import ru.alfacampus.homeworkproject.featureCharactersDescription.data.ds.ComicsService
import ru.alfacampus.homeworkproject.coreData.data.entities.comics.ComicsResponseEntity
import ru.alfacampus.homeworkproject.featureCharactersDescription.data.mappers.ComicsMapper
import javax.inject.Inject


class ComicsRepositoryImpl @Inject constructor(
    private val comicsService: ComicsService,
    private val toEntityMapper: ComicsMapper
) : ComicsRepository {

    override suspend fun searchComicsByCharacterId(characterId: Int, limit: Int)
            : Resource<ComicsResponseEntity> {
        val comicsResponse = comicsService.getComicsByCharacterId(characterId, limit)
        return if (comicsResponse.isSuccessful)
            Resource.Success(comicsResponse.body()
                ?.let { toEntityMapper.comicsResponseMapToEntity(it) })
        else
            Resource.Error(comicsResponse.message())
    }
}
