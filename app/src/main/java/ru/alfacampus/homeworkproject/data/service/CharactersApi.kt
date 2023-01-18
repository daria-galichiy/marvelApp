package ru.alfacampus.homeworkproject.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.alfacampus.homeworkproject.data.dto.CharactersResponse

interface CharactersApi {

    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<CharactersResponse>
}
