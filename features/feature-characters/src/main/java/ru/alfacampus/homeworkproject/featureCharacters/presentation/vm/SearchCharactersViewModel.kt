package ru.alfacampus.homeworkproject.featureCharacters.presentation.vm

import android.graphics.Bitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
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
import ru.alfacampus.homeworkproject.navigation.DeepLinkDestination
import ru.alfacampus.homeworkproject.navigation.deepLinkNavigateTo
import javax.inject.Inject


class SearchCharactersViewModel @Inject constructor(
    private val repository: CharactersRepository
) : CharacterInteractionListener, ViewModel() {

    private val mutableSearchCharactersStateFlow =
        MutableStateFlow<Resource<CharactersResponseEntity>>(Resource.Empty())
    val searchCharactersStateFlow: StateFlow<Resource<CharactersResponseEntity>>
        get() = mutableSearchCharactersStateFlow

    private val mutableShareFoundCharacterFlow = MutableStateFlow<CharacterMarvelEntity?>(null)
    val shareFoundCharacterStateFlow: StateFlow<CharacterMarvelEntity?> get() = mutableShareFoundCharacterFlow
    var foundCharacterImageBitmap: Bitmap? = null

    private var searchCharactersByNameJob: Job? = null
    private var saveCharacterToFavoritesJob: Job? = null

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

    override fun onShareCharacterClicked(character: CharacterMarvelEntity, bitmap: Bitmap) {
        foundCharacterImageBitmap = bitmap
        mutableShareFoundCharacterFlow.update { character }
    }

    fun onFoundCharacterShared() {
        foundCharacterImageBitmap = null
        mutableShareFoundCharacterFlow.update { null }
    }

    private fun saveCharacterToFavorites(character: CharacterMarvelEntity) {
        saveCharacterToFavoritesJob?.cancel()
        saveCharacterToFavoritesJob = viewModelScope.launch(Dispatchers.IO) {
            repository.addCharacterToFavorites(character)
        }
    }

    fun navigateToCharactersDescription(fragment: Fragment, character: CharacterMarvelEntity) {
        NavHostFragment.findNavController(fragment).deepLinkNavigateTo(DeepLinkDestination.CharacterDescriptionDestination(character))
    }
}
