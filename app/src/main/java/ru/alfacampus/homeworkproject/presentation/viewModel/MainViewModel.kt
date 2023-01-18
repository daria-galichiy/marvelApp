package ru.alfacampus.homeworkproject.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.alfacampus.homeworkproject.data.dto.CharactersResponse
import ru.alfacampus.homeworkproject.data.repository.TestRepository
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: TestRepository): ViewModel() {

    private val charactersMarvel = MutableLiveData<CharactersResponse>()

    init {
        getCharacters()
    }

    fun getCharacters() = viewModelScope.launch {
        repository.getCharacters().let { response ->
            if (response.isSuccessful)
                charactersMarvel.postValue(response.body())
            else
                Log.d("checkData", "Failed to load marvel characters: ${response.errorBody()}")
        }
    }
}
