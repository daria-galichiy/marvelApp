package ru.alfacampus.homeworkproject.featureFavoriteCharacters.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.alfacampus.homeworkproject.coreDi.vm.ViewModelKey
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.data.repository.FavoriteCharactersRepository
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.data.repository.FavoriteCharactersRepositoryImpl
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.presentation.vm.FavoriteCharactersViewModel

@Module
interface FavoriteCharactersBindsModule {
    @Binds
    fun bindFavoriteCharactersRepository(favoriteCharactersRepository: FavoriteCharactersRepositoryImpl):
            FavoriteCharactersRepository

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteCharactersViewModel::class)
    fun bindFavoriteCharactersViewModel(favoriteCharactersViewModel: FavoriteCharactersViewModel): ViewModel
}
