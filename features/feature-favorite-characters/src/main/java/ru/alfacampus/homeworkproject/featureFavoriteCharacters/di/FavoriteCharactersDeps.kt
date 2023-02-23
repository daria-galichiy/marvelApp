package ru.alfacampus.homeworkproject.featureFavoriteCharacters.di

import android.content.Context
import ru.alfacampus.homeworkproject.coreDb.db.dao.CharactersDao
import ru.alfacampus.homeworkproject.coreDb.db.mappers.CharacterDbMapper
import ru.alfacampus.homeworkproject.coreDi.dependencies.FeatureExternalDeps

interface FavoriteCharactersDeps : FeatureExternalDeps {
//    val application: Application
    val context: Context
    val charactersDao: CharactersDao
    val dbMapper: CharacterDbMapper
}
