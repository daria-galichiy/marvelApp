package ru.alfacampus.homeworkproject.featureCharactersDescription.presentation.vm

import androidx.lifecycle.ViewModel
import ru.alfacampus.homeworkproject.featureCharactersDescription.di.CharactersDescriptionComponent
import ru.alfacampus.homeworkproject.featureCharactersDescription.di.CharactersDescriptionDeps
import ru.alfacampus.homeworkproject.featureCharactersDescription.di.DaggerCharactersDescriptionComponent
import ru.alfacampus.homeworkproject.featureCharactersDescription.presentation.vm.CharactersDescriptionComponentDepsProvider.charactersDescriptionDeps


class CharactersDescriptionComponentViewModel : ViewModel() {

    val charactersDescriptionComponent: CharactersDescriptionComponent by lazy {
        DaggerCharactersDescriptionComponent.factory()
            .create(checkNotNull(charactersDescriptionDeps))
    }

    override fun onCleared() {
        super.onCleared()
        charactersDescriptionDeps = null
    }
}

object CharactersDescriptionComponentDepsProvider {
    var charactersDescriptionDeps: CharactersDescriptionDeps? = null
}
