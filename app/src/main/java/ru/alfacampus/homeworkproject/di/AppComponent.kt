package ru.alfacampus.homeworkproject.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.alfacampus.featureCreatedCharacters.di.CreatedCharactersDeps
import ru.alfacampus.homeworkproject.coreDb.di.DatabaseModule
import ru.alfacampus.homeworkproject.coreDi.scopes.AppScope
import ru.alfacampus.homeworkproject.coreNetwork.di.NetworkModule
import ru.alfacampus.homeworkproject.featureCharacters.di.CharactersDeps
import ru.alfacampus.homeworkproject.featureCharactersDescription.di.CharactersDescriptionDeps
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.di.FavoriteCharactersDeps
import ru.alfacampus.homeworkproject.navigation.Navigator
import ru.alfacampus.homeworkproject.presentation.ui.MainActivity
import javax.inject.Singleton


@AppScope
@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        FeatureExternalDepsModule::class,
        NavigatorModule::class
    ]
)
interface AppComponent :
    CharactersDeps,
    CharactersDescriptionDeps,
    FavoriteCharactersDeps,
    CreatedCharactersDeps {

    fun inject(appActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance navigator: Navigator
        ): AppComponent
    }
}
