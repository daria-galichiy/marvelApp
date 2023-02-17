package ru.alfacampus.homeworkproject.featureCharacters.presentation.vm

import androidx.lifecycle.ViewModel
import ru.alfacampus.homeworkproject.featureCharacters.di.CharactersComponent
import ru.alfacampus.homeworkproject.featureCharacters.di.CharactersDeps
import ru.alfacampus.homeworkproject.featureCharacters.di.DaggerCharactersComponent
import ru.alfacampus.homeworkproject.featureCharacters.presentation.vm.CharactersComponentDepsProvider.charactersDeps

class CharactersComponentViewModel : ViewModel() {

    val charactersComponent: CharactersComponent by lazy {
        DaggerCharactersComponent.factory()
            .create(checkNotNull(charactersDeps))
    }

    override fun onCleared() {
        super.onCleared()
        charactersDeps = null
    }
}

object CharactersComponentDepsProvider {
    var charactersDeps: CharactersDeps? = null
}
