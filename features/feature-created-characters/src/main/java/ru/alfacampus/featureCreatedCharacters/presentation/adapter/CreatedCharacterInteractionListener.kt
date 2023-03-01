package ru.alfacampus.featureCreatedCharacters.presentation.adapter

import ru.alfacampus.homeworkproject.coreData.data.entities.character.CreatedCharacterEntity

interface CreatedCharacterInteractionListener {
    fun onCreateCharacterClicked(character: CreatedCharacterEntity)
    fun onDeleteCreatedCharacterClicked(character: CreatedCharacterEntity)
}
