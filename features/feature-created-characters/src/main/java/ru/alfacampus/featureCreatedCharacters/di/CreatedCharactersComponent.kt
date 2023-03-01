package ru.alfacampus.featureCreatedCharacters.di

import dagger.Component
import ru.alfacampus.featureCreatedCharacters.presentation.ui.CharacterCreationFragment
import ru.alfacampus.featureCreatedCharacters.presentation.ui.CreatedCharactersFragment
import ru.alfacampus.homeworkproject.coreDi.scopes.FeatureScope

@FeatureScope
@Component(
    modules = [CreatedCharactersBindsModule::class],
    dependencies = [CreatedCharactersDeps::class]
)
interface CreatedCharactersComponent {

    fun inject(createdCharactersFragment: CreatedCharactersFragment)

    fun inject(characterCreationFragment: CharacterCreationFragment)

    @Component.Factory
    interface Factory {
        fun create(createdCharactersDeps: CreatedCharactersDeps): CreatedCharactersComponent
    }
}
