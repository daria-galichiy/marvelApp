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
class MainViewModel @Inject constructor(private val repository: MarvelRepository) : ViewModel(),
    CharacterInteractionListener {

    val charactersMarvelLiveData: MutableLiveData<Resource<CharactersResponse>> = MutableLiveData()

    init {
        getCharacters(0, 50)
    }

    private fun getCharacters(offset: Int, limit: Int) = viewModelScope.launch {
        charactersMarvelLiveData.postValue(Resource.Loading())
        val response = repository.getCharacters(offset, limit)
        if (response.isSuccessful) {
            response.body().let { res ->
                charactersMarvelLiveData.postValue(Resource.Success(res))
            }
        } else {
            charactersMarvelLiveData.postValue(Resource.Error(response.message()))
        }
    }

    fun getFavoriteCharacters() = repository.getFavoriteCharacters()

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
