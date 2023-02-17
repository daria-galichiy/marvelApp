package ru.alfacampus.homeworkproject.featureCharacters.data.ds

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.alfacampus.homeworkproject.featureCharacters.data.dto.character.CharactersResponse


interface CharactersService {

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
}
