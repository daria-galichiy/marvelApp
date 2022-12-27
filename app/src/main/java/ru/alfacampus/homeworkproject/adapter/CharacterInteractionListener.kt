package ru.alfacampus.homeworkproject.adapter

import ru.alfacampus.homeworkproject.dto.CharacterMarvel

interface CharacterInteractionListener {
    fun onAddClicked()
    fun onRemoveClicked(character: CharacterMarvel)
}
