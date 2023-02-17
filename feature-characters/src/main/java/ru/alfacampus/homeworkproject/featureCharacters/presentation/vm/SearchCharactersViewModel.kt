package ru.alfacampus.homeworkproject.featureCharacters.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.alfacampus.homeworkproject.coreNetwork.utils.Resource
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharactersResponseEntity
import ru.alfacampus.homeworkproject.featureCharacters.data.repository.CharactersRepository
import ru.alfacampus.homeworkproject.featureCharacters.presentation.adapter.CharacterInteractionListener
import javax.inject.Inject


class SearchCharactersViewModel @Inject constructor(
    private val repository: CharactersRepository
) : CharacterInteractionListener, ViewModel() {

    private val mutableSearchCharactersStateFlow =
        MutableStateFlow<Resource<CharactersResponseEntity>>(Resource.Empty())
    val searchCharactersStateFlow: StateFlow<Resource<CharactersResponseEntity>>
        get() = mutableSearchCharactersStateFlow

    private var searchCharactersByNameJob: Job? = null
    private var saveCharacterToFavoritesJob: Job? = null
    private var deleteCharacterFromFavoritesJob: Job? = null

    fun searchCharactersByNameStartsWith(nameStartsWith: String, limit: Int) {
        searchCharactersByNameJob?.cancel()
        searchCharactersByNameJob = viewModelScope.launch(Dispatchers.IO) {
            mutableSearchCharactersStateFlow.update { Resource.Loading() }
            val characters = repository.searchCharacters(nameStartsWith, limit)
            mutableSearchCharactersStateFlow.update { characters }
        }
    }

    override fun onAddCharacterToFavoritesClicked(character: CharacterMarvelEntity) {
        saveCharacterToFavorites(character)
    }

    private fun saveCharacterToFavorites(character: CharacterMarvelEntity) {
        saveCharacterToFavoritesJob?.cancel()
        saveCharacterToFavoritesJob = viewModelScope.launch(Dispatchers.IO) {
            repository.addCharacterToFavorites(character)
        }
    }
}
