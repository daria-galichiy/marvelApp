package ru.alfacampus.homeworkproject.featureCharacters.di

import android.content.Context
import retrofit2.Retrofit
import ru.alfacampus.homeworkproject.coreDb.db.dao.CharactersDao
import ru.alfacampus.homeworkproject.coreDb.db.mappers.CharacterDbMapper
import ru.alfacampus.homeworkproject.coreDi.dependencies.FeatureExternalDeps

interface CharactersDeps : FeatureExternalDeps {
//    val application: Application
    val context: Context
    val retrofit: Retrofit
    val charactersDao: CharactersDao
    val dbMapper: CharacterDbMapper
}
