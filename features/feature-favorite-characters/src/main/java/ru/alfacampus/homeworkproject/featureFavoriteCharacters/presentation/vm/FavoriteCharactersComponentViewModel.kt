package ru.alfacampus.homeworkproject.featureFavoriteCharacters.presentation.vm

import androidx.lifecycle.ViewModel
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.di.DaggerFavoriteCharactersComponent
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.di.FavoriteCharactersComponent
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.di.FavoriteCharactersDeps
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.presentation.vm.FavoriteCharactersDepsProvider.favoriteCharactersDeps

class FavoriteCharactersComponentViewModel : ViewModel() {

    val favoriteCharactersComponent: FavoriteCharactersComponent by lazy {
        DaggerFavoriteCharactersComponent.factory()
            .create(checkNotNull(favoriteCharactersDeps))
    }

    override fun onCleared() {
        super.onCleared()
        favoriteCharactersDeps = null
    }
}

object FavoriteCharactersDepsProvider {
    var favoriteCharactersDeps: FavoriteCharactersDeps? = null
}
