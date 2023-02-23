package ru.alfacampus.homeworkproject.featureCharacters.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.create
import ru.alfacampus.homeworkproject.coreDi.vm.ViewModelKey
import ru.alfacampus.homeworkproject.featureCharacters.data.ds.CharactersService
import ru.alfacampus.homeworkproject.featureCharacters.data.repository.CharactersRepository
import ru.alfacampus.homeworkproject.featureCharacters.data.repository.CharactersRepositoryImpl
import ru.alfacampus.homeworkproject.featureCharacters.presentation.vm.CharactersViewModel
import ru.alfacampus.homeworkproject.featureCharacters.presentation.vm.SearchCharactersViewModel

@Module
class CharactersProvidesModule {
    @Provides
    fun provideCharactersService(retrofit: Retrofit): CharactersService = retrofit.create()
}

@Module
interface CharactersBindsModule {
    @Binds
    fun bindCharactersRepository(charactersRepository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    fun bindCharactersViewModel(charactersViewModel: CharactersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchCharactersViewModel::class)
    fun bindSearchCharactersViewModel(searchCharactersViewModel: SearchCharactersViewModel): ViewModel
}
