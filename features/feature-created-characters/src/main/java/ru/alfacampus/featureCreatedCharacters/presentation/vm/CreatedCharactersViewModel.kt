package ru.alfacampus.featureCreatedCharacters.presentation.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.alfacampus.featureCreatedCharacters.data.repository.CreatedCharactersRepository
import ru.alfacampus.featureCreatedCharacters.presentation.adapter.CreatedCharacterInteractionListener
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CreatedCharacterEntity
import javax.inject.Inject

class CreatedCharactersViewModel @Inject constructor(
    private val repository: CreatedCharactersRepository
) : CreatedCharacterInteractionListener, ViewModel() {

    private val mutableCharacterStateFlow = MutableStateFlow<CreatedCharacterEntity?>(null)
    val characterStateFlow: StateFlow<CreatedCharacterEntity?> get() = mutableCharacterStateFlow

    private var createCharacterJob: Job? = null
    private var getCharacterJob: Job? = null
    private var deleteCreatedCharacterJob: Job? = null

    fun getCreatedCharacters() = repository.getCreatedCharacters()

    fun getCreatedCharacterById(createdCharacterId: Int) {
        getCharacterJob?.cancel()
        getCharacterJob = viewModelScope.launch(Dispatchers.IO) {
            val character = repository.getCreatedCharacterById(createdCharacterId)
            mutableCharacterStateFlow.update { character }
        }
    }

    fun resetCharacter() {
        mutableCharacterStateFlow.update { null }
    }

    private fun createCharacter(character: CreatedCharacterEntity) {
        createCharacterJob?.cancel()
        createCharacterJob = viewModelScope.launch(Dispatchers.IO) {
            repository.createCharacter(character)
        }
    }

    private fun deleteCreatedCharacter(character: CreatedCharacterEntity) {
        deleteCreatedCharacterJob?.cancel()
        deleteCreatedCharacterJob = viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCreatedCharacter(character)
        }
    }

    override fun onCreateCharacterClicked(character: CreatedCharacterEntity) {
        createCharacter(character)
    }

    override fun onDeleteCreatedCharacterClicked(character: CreatedCharacterEntity) {
        deleteCreatedCharacter(character)
    }
}
