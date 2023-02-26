package ru.alfacampus.homeworkproject.featureCharacters.presentation.adapter

import android.graphics.Bitmap
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity

interface CharacterInteractionListener {
    fun onAddCharacterToFavoritesClicked(character: CharacterMarvelEntity)
    fun onShareCharacterClicked(character: CharacterMarvelEntity, bitmap: Bitmap)
}
