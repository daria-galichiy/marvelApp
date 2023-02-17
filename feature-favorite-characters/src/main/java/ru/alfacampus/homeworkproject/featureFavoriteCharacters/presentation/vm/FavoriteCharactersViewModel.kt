package ru.alfacampus.homeworkproject.featureFavoriteCharacters.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.featureCharacters.presentation.adapter.FavoriteCharacterInteractionListener
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.data.repository.FavoriteCharactersRepository
import javax.inject.Inject


class FavoriteCharactersViewModel @Inject constructor(
    private val repository: FavoriteCharactersRepository
) : FavoriteCharacterInteractionListener, ViewModel() {

    private var saveCharacterToFavoritesJob: Job? = null
    private var deleteCharacterFromFavoritesJob: Job? = null

    fun getFavoriteCharacters() = repository.getFavoriteCharacters()

    private fun saveCharacterToFavorites(character: CharacterMarvelEntity) {
        saveCharacterToFavoritesJob?.cancel()
        saveCharacterToFavoritesJob = viewModelScope.launch(Dispatchers.IO) {
            repository.addCharacterToFavorites(character)
        }
    }

    fun deleteCharacterFromFavorites(character: CharacterMarvelEntity) {
        deleteCharacterFromFavoritesJob?.cancel()
        deleteCharacterFromFavoritesJob = viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCharacterFromFavorites(character)
        }
    }

    override fun onAddCharacterToFavoritesClicked(character: CharacterMarvelEntity) {
        saveCharacterToFavorites(character)
    }

    override fun onDeleteCharacterFromFavoritesClicked(character: CharacterMarvelEntity) {
        deleteCharacterFromFavorites(character)
    }
}
