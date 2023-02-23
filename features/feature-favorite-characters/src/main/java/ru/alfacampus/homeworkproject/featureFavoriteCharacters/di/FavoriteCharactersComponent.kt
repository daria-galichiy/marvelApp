package ru.alfacampus.homeworkproject.featureFavoriteCharacters.di

import dagger.Component
import ru.alfacampus.homeworkproject.coreDi.scopes.FeatureScope
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.presentation.ui.FavoriteCharactersFragment

@FeatureScope
@Component(
    modules = [FavoriteCharactersBindsModule::class],
    dependencies = [FavoriteCharactersDeps::class]
)
interface FavoriteCharactersComponent {

    fun inject(favoriteCharactersFragment: FavoriteCharactersFragment)

    @Component.Factory
    interface Factory {
        fun create(favoriteCharactersDeps: FavoriteCharactersDeps): FavoriteCharactersComponent
    }
}
