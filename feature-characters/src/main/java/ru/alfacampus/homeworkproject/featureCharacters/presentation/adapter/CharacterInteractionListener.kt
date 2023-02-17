package ru.alfacampus.homeworkproject.featureCharacters.presentation.adapter

import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity

interface CharacterInteractionListener {
    fun onAddCharacterToFavoritesClicked(character: CharacterMarvelEntity)
}
