package ru.alfacampus.homeworkproject.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.alfacampus.homeworkproject.data.dto.comics.ComicsResponse
import ru.alfacampus.homeworkproject.data.repository.MarvelRepository
import ru.alfacampus.homeworkproject.utils.Resource
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(private val repository: MarvelRepository) : ViewModel() {

    val comicsLiveData: MutableLiveData<Resource<ComicsResponse>> = MutableLiveData()

    private var getComicsByIdJob: Job? = null

    fun getComicsByCharacterId(characterId: Int, limit: Int) {
        getComicsByIdJob?.cancel()
        getComicsByIdJob = viewModelScope.launch {
            comicsLiveData.postValue(Resource.Loading())
            val response = repository.searchComicsByCharacterId(characterId, limit)
            if (response.isSuccessful) {
                response.body().let { comicsResponse ->
                    comicsLiveData.postValue(Resource.Success(comicsResponse))
                }
            } else {
                comicsLiveData.postValue(Resource.Error(response.message()))
            }
        }
    }
}
