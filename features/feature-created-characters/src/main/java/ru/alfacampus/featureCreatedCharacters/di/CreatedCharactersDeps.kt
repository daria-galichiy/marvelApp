package ru.alfacampus.featureCreatedCharacters.di

import android.content.Context
import ru.alfacampus.homeworkproject.coreDb.db.dao.CreatedCharactersDao
import ru.alfacampus.homeworkproject.coreDb.db.mappers.CreatedCharacterDbMapper
import ru.alfacampus.homeworkproject.coreDi.dependencies.FeatureExternalDeps

interface CreatedCharactersDeps : FeatureExternalDeps {
    val context: Context
    val createdCharactersDao: CreatedCharactersDao
    val createdCharacterDbMapper: CreatedCharacterDbMapper
}
