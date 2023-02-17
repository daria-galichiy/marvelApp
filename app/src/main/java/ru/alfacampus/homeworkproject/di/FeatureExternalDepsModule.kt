package ru.alfacampus.homeworkproject.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.alfacampus.homeworkproject.coreDi.dependencies.FeatureExternalDeps
import ru.alfacampus.homeworkproject.coreDi.dependencies.FeatureExternalDepsKey
import ru.alfacampus.homeworkproject.featureCharacters.di.CharactersDeps
import ru.alfacampus.homeworkproject.featureCharactersDescription.di.CharactersDescriptionDeps
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.di.FavoriteCharactersDeps

@Module
interface FeatureExternalDepsModule {

    @Binds
    @IntoMap
    @FeatureExternalDepsKey(CharactersDeps::class)
    fun bindCharactersDeps(appComponent: AppComponent): FeatureExternalDeps

    @Binds
    @IntoMap
    @FeatureExternalDepsKey(CharactersDescriptionDeps::class)
    fun bindCharactersDescriptionDeps(appComponent: AppComponent): FeatureExternalDeps

    @Binds
    @IntoMap
    @FeatureExternalDepsKey(FavoriteCharactersDeps::class)
    fun bindFavoriteCharactersDeps(appComponent: AppComponent): FeatureExternalDeps
}
