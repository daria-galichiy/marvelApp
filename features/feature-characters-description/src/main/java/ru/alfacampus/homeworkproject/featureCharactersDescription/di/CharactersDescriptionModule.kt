package ru.alfacampus.homeworkproject.featureCharactersDescription.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.create
import ru.alfacampus.homeworkproject.coreDi.vm.ViewModelKey
import ru.alfacampus.homeworkproject.featureCharactersDescription.data.ds.ComicsService
import ru.alfacampus.homeworkproject.featureCharactersDescription.data.repository.ComicsRepository
import ru.alfacampus.homeworkproject.featureCharactersDescription.data.repository.ComicsRepositoryImpl
import ru.alfacampus.homeworkproject.featureCharactersDescription.presentation.vm.ComicsViewModel


@Module
class CharactersDescriptionProvidesModule {
    @Provides
    fun provideComicsService(retrofit: Retrofit): ComicsService = retrofit.create()
}

@Module
interface CharactersDescriptionBindsModule {
    @Binds
    fun bindComicsRepository(comicsRepositoryImpl: ComicsRepositoryImpl): ComicsRepository

    @Binds
    @IntoMap
    @ViewModelKey(ComicsViewModel::class)
    fun bindComicsViewModel(comicsViewModel: ComicsViewModel): ViewModel
}
