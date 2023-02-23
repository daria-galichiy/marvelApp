package ru.alfacampus.homeworkproject.featureCharactersDescription.di

import retrofit2.Retrofit
import ru.alfacampus.homeworkproject.coreDi.dependencies.FeatureExternalDeps

interface CharactersDescriptionDeps : FeatureExternalDeps {
    val retrofit: Retrofit
}
