package ru.alfacampus.homeworkproject.featureCharactersDescription.data.ds

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.alfacampus.homeworkproject.featureCharactersDescription.data.dto.comics.ComicsResponse

interface ComicsService {
    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getComicsByCharacterId(
        @Path("characterId") characterId: Int,
        @Query("limit") limit: Int = 10
    ): Response<ComicsResponse>
}
