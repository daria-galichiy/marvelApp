package ru.alfacampus.homeworkproject.featureCharacters.di

import dagger.Component
import ru.alfacampus.homeworkproject.coreDi.scopes.FeatureScope
import ru.alfacampus.homeworkproject.featureCharacters.presentation.ui.CharactersListScreenFragment

@FeatureScope
@Component(
    modules = [CharactersProvidesModule::class, CharactersBindsModule::class],
    dependencies = [CharactersDeps::class]
)
interface CharactersComponent {

    fun inject(charactersListScreenFragment: CharactersListScreenFragment)

    @Component.Factory
    interface Factory {
        fun create(charactersDeps: CharactersDeps): CharactersComponent
    }
}
