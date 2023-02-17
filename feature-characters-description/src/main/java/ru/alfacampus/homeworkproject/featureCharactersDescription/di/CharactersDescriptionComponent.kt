package ru.alfacampus.homeworkproject.featureCharactersDescription.di

import dagger.Component
import ru.alfacampus.homeworkproject.coreDi.scopes.FeatureScope
import ru.alfacampus.homeworkproject.featureCharactersDescription.presentation.ui.CharactersDescriptionFragment


@Component(
    modules = [CharactersDescriptionProvidesModule::class, CharactersDescriptionBindsModule::class],
    dependencies = [CharactersDescriptionDeps::class]
)
@FeatureScope
interface CharactersDescriptionComponent {

    fun inject(characterDescriptionFragment: CharactersDescriptionFragment)

    @Component.Factory
    interface Factory {
        fun create(charactersDescriptionDeps: CharactersDescriptionDeps): CharactersDescriptionComponent
    }
}
