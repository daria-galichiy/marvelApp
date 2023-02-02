package ru.alfacampus.homeworkproject.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.alfacampus.homeworkproject.data.dto.character.CharactersResponse
import ru.alfacampus.homeworkproject.data.dto.comics.ComicsResponse

interface CharactersApi {

    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 10
    ): Response<CharactersResponse>

    @GET("v1/public/characters")
    suspend fun getCharactersByNameStartsWith(
        @Query("nameStartsWith") nameStartsWith: String,
        @Query("limit") limit: Int = 10
    ): Response<CharactersResponse>

    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getComicsByCharacterId(
        @Path("characterId") characterId: Int,
        @Query("limit") limit: Int = 10
    ): Response<ComicsResponse>
}
