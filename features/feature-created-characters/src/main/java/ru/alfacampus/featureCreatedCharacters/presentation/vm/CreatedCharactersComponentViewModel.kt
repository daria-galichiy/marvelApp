package ru.alfacampus.featureCreatedCharacters.presentation.vm

import androidx.lifecycle.ViewModel
import ru.alfacampus.featureCreatedCharacters.di.CreatedCharactersComponent
import ru.alfacampus.featureCreatedCharacters.di.CreatedCharactersDeps
import ru.alfacampus.featureCreatedCharacters.di.DaggerCreatedCharactersComponent
import ru.alfacampus.featureCreatedCharacters.presentation.vm.CreatedCharactersDepsProvider.createdCharactersDeps

class CreatedCharactersComponentViewModel: ViewModel() {

    val createdCharactersComponent: CreatedCharactersComponent by lazy {
        DaggerCreatedCharactersComponent.factory()
            .create(checkNotNull(createdCharactersDeps))
    }

    override fun onCleared() {
        super.onCleared()
        createdCharactersDeps = null
    }
}

object CreatedCharactersDepsProvider {
    var createdCharactersDeps: CreatedCharactersDeps? = null
}
