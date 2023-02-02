package ru.alfacampus.homeworkproject.presentation.adapter

import ru.alfacampus.homeworkproject.data.dto.character.CharacterMarvel

interface CharacterInteractionListener {
    fun onAddCharacterToFavoritesClicked(character: CharacterMarvel)
    fun onRemoveCharacterFromFavoritesClicked(character: CharacterMarvel)
}
