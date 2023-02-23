package ru.alfacampus.homeworkproject.featureCharactersDescription.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.alfacampus.homeworkproject.coreNetwork.utils.Resource
import ru.alfacampus.homeworkproject.coreData.data.entities.comics.ComicsResponseEntity
import ru.alfacampus.homeworkproject.featureCharactersDescription.data.repository.ComicsRepository
import javax.inject.Inject


class ComicsViewModel @Inject constructor(
    private val repository: ComicsRepository
) : ViewModel() {

    private val mutableComicsStateFlow =
        MutableStateFlow<Resource<ComicsResponseEntity>>(Resource.Loading())
    val comicsStateFlow: StateFlow<Resource<ComicsResponseEntity>> get() = mutableComicsStateFlow

    private var getComicsByIdJob: Job? = null

    fun getComicsByCharacterId(characterId: Int, limit: Int) {
        getComicsByIdJob?.cancel()
        getComicsByIdJob = viewModelScope.launch(Dispatchers.IO) {
            val comicsResponse = repository.searchComicsByCharacterId(characterId, limit)
            mutableComicsStateFlow.update { comicsResponse }
        }
    }
}
