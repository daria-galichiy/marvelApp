package ru.alfacampus.homeworkproject.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.alfacampus.homeworkproject.data.dto.character.CharacterMarvel
import ru.alfacampus.homeworkproject.data.dto.character.CharactersResponse
import ru.alfacampus.homeworkproject.data.repository.MarvelRepository
import ru.alfacampus.homeworkproject.presentation.adapter.CharacterInteractionListener
import ru.alfacampus.homeworkproject.utils.Resource
import javax.inject.Inject

@HiltViewModel
class SearchCharactersViewModel @Inject constructor(private val repository: MarvelRepository) :
    ViewModel(), CharacterInteractionListener {

    val searchCharactersLiveData: MutableLiveData<Resource<CharactersResponse>> = MutableLiveData()

    fun searchCharactersByNameStartsWith(nameStartsWith: String, limit: Int) =
        viewModelScope.launch {
            searchCharactersLiveData.postValue(Resource.Loading())
            val response = repository.searchCharacters(nameStartsWith, limit)
            if (response.isSuccessful) {
                response.body().let { charactersResponse ->
                    searchCharactersLiveData.postValue(Resource.Success(charactersResponse))
                }
            } else {
                searchCharactersLiveData.postValue(Resource.Error(response.message()))
            }
        }

    private fun saveCharacterToFavorites(character: CharacterMarvel) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCharacterToFavorites(character)
        }

    private fun deleteCharacterFromFavorites(character: CharacterMarvel) = viewModelScope.launch {
        repository.deleteCharacterFromFavorites(character)
    }

    override fun onAddCharacterToFavoritesClicked(character: CharacterMarvel) {
        saveCharacterToFavorites(character)
    }

    override fun onRemoveCharacterFromFavoritesClicked(character: CharacterMarvel) {
        deleteCharacterFromFavorites(character)
    }
}
