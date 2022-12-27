package ru.alfacampus.homeworkproject.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.alfacampus.homeworkproject.adapter.CharacterInteractionListener
import ru.alfacampus.homeworkproject.data.CharactersRepository
import ru.alfacampus.homeworkproject.data.impl.InMemoryCharactersRepository
import ru.alfacampus.homeworkproject.dto.CharacterMarvel


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
            thumbnail = ""
        )
        repository.save(character)
    }

    override fun onRemoveClicked(character: CharacterMarvel) {
        repository.delete(character.id)
    }
}
