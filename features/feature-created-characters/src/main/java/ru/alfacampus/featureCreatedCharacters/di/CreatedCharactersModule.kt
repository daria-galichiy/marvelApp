package ru.alfacampus.featureCreatedCharacters.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.alfacampus.featureCreatedCharacters.data.repository.CreatedCharactersRepository
import ru.alfacampus.featureCreatedCharacters.data.repository.CreatedCharactersRepositoryImpl
import ru.alfacampus.featureCreatedCharacters.presentation.vm.CreatedCharactersViewModel
import ru.alfacampus.homeworkproject.coreDi.vm.ViewModelKey

@Module
interface CreatedCharactersBindsModule {
    @Binds
    fun bindCreatedCharactersRepository(createdCharactersRepository: CreatedCharactersRepositoryImpl):
            CreatedCharactersRepository

    @Binds
    @IntoMap
    @ViewModelKey(CreatedCharactersViewModel::class)
    fun bindCreatedCharactersViewModel(createdCharactersViewModel: CreatedCharactersViewModel): ViewModel
}
