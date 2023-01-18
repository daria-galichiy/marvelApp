package ru.alfacampus.homeworkproject.data.repository

import ru.alfacampus.homeworkproject.data.service.CharactersApi
import javax.inject.Inject

class TestRepository @Inject constructor(private val charactersApi: CharactersApi){
    suspend fun getCharacters() = charactersApi.getCharacters(0, 10)
}
