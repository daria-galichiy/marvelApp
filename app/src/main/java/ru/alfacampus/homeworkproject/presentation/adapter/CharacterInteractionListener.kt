package ru.alfacampus.homeworkproject.presentation.adapter

import ru.alfacampus.homeworkproject.data.dto.character.CharacterMarvel

interface CharacterInteractionListener {
    fun onAddToFavoritesClicked(character: CharacterMarvel)
    fun onRemoveClicked(character: CharacterMarvel)
}
