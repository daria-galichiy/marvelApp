package ru.alfacampus.homeworkproject.presentation.adapter

import ru.alfacampus.homeworkproject.data.dto.CharacterMarvel

interface CharacterInteractionListener {
    fun onAddClicked()
    fun onRemoveClicked(character: CharacterMarvel)
}
