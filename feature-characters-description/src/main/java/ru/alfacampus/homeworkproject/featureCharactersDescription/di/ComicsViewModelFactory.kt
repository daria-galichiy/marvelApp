package ru.alfacampus.homeworkproject.featureCharactersDescription.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.alfacampus.homeworkproject.featureCharactersDescription.data.repository.ComicsRepository
import ru.alfacampus.homeworkproject.featureCharactersDescription.presentation.vm.ComicsViewModel
import javax.inject.Inject
import javax.inject.Provider


class ComicsViewModelFactory @Inject constructor(
    private val repository: Provider<ComicsRepository>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == ComicsViewModel::class.java)
        return ComicsViewModel(repository.get()) as T
    }
}
