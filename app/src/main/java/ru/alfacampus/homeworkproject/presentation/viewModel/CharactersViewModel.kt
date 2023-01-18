package ru.alfacampus.homeworkproject.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.alfacampus.homeworkproject.presentation.adapter.CharacterInteractionListener
import ru.alfacampus.homeworkproject.data.repository.CharactersRepository
import ru.alfacampus.homeworkproject.data.repository.impl.InMemoryCharactersRepository
import ru.alfacampus.homeworkproject.data.dto.temporarystub.CharacterMarvel


class CharactersViewModel(
    application: Application
) : AndroidViewModel(application),
    CharacterInteractionListener {

    private val repository: CharactersRepository = InMemoryCharactersRepository()

    val data by repository::data

    override fun onAddClicked() {
        val character = CharacterMarvel(
            id = CharactersRepository.NEW_CHARACTER_ID,
            name = "",
            simpleImage = ""
        )
        repository.save(character)
    }

    override fun onRemoveClicked(character: CharacterMarvel) {
        repository.delete(character.id)
    }
}
