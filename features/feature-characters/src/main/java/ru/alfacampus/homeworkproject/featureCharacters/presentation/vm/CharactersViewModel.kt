package ru.alfacampus.homeworkproject.featureCharacters.presentation.vm

import android.graphics.Bitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharactersResponseEntity
import ru.alfacampus.homeworkproject.coreNetwork.utils.Resource
import ru.alfacampus.homeworkproject.featureCharacters.data.repository.CharactersRepository
import ru.alfacampus.homeworkproject.featureCharacters.presentation.adapter.CharacterInteractionListener
import ru.alfacampus.homeworkproject.navigation.DeepLinkDestination
import ru.alfacampus.homeworkproject.navigation.deepLinkNavigateTo
import javax.inject.Inject


class CharactersViewModel @Inject constructor(
    private val repository: CharactersRepository
) : CharacterInteractionListener, ViewModel() {

    private val mutableCharactersStateFlow =
        MutableStateFlow<Resource<CharactersResponseEntity>>(Resource.Loading())
    val charactersStateFlow: StateFlow<Resource<CharactersResponseEntity>> get() = mutableCharactersStateFlow

    private val mutableShareCharacterFlow = MutableStateFlow<CharacterMarvelEntity?>(null)
    val shareCharacterStateFlow: StateFlow<CharacterMarvelEntity?> get() = mutableShareCharacterFlow
    var characterImageBitmap: Bitmap? = null

    private var getCharactersJob: Job? = null
    private var saveCharacterToFavoritesJob: Job? = null

    init {
        getCharacters(0, 50)
    }

    override fun onAddCharacterToFavoritesClicked(character: CharacterMarvelEntity) {
        saveCharacterToFavorites(character)
    }

    override fun onShareCharacterClicked(character: CharacterMarvelEntity, bitmap: Bitmap) {
        characterImageBitmap = bitmap
        mutableShareCharacterFlow.update { character }
    }

    fun onCharacterShared() {
        characterImageBitmap = null
        mutableShareCharacterFlow.update { null }
    }

    private fun getCharacters(offset: Int, limit: Int) {
        getCharactersJob?.cancel()
        getCharactersJob = viewModelScope.launch(Dispatchers.IO) {
            val charactersResponse = repository.getCharacters(offset, limit)
            mutableCharactersStateFlow.update { charactersResponse }
        }
    }

    private fun saveCharacterToFavorites(character: CharacterMarvelEntity) {
        saveCharacterToFavoritesJob?.cancel()
        saveCharacterToFavoritesJob = viewModelScope.launch(Dispatchers.IO) {
            repository.addCharacterToFavorites(character)
        }
    }

    fun navigateToCharactersDescription(fragment: Fragment, character: CharacterMarvelEntity) {
        findNavController(fragment).deepLinkNavigateTo(
            DeepLinkDestination.CharacterDescriptionDestination(
                character
            )
        )
    }

    fun navigateToStartScreen(fragment: Fragment) {
        findNavController(fragment).popBackStack()
        findNavController(fragment).deepLinkNavigateTo(DeepLinkDestination.StartScreenDestination())
    }
}
