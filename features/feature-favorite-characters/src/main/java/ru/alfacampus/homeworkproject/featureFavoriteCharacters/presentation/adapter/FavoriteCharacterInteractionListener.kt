package ru.alfacampus.homeworkproject.featureCharacters.presentation.adapter

import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity

interface FavoriteCharacterInteractionListener {
    fun onAddCharacterToFavoritesClicked(character: CharacterMarvelEntity)
    fun onDeleteCharacterFromFavoritesClicked(character: CharacterMarvelEntity)
}
