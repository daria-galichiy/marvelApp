package ru.alfacampus.homeworkproject.featureCharacters.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.featureCharacters.presentation.vm.CharactersViewModel
import ru.alfacampus.homeworkproject.featureCharacters.presentation.vm.SearchCharactersViewModel
import ru.alfacampus.homeworkproject.resources.R


class SendIntentCreator {
    fun createCharacterSendIntent(
        context: Context,
        viewModel: ViewModel,
        character: CharacterMarvelEntity,
    ) {
        try {
            val bitmapImage = if (viewModel is CharactersViewModel)
                viewModel.characterImageBitmap
            else if (viewModel is SearchCharactersViewModel)
                viewModel.foundCharacterImageBitmap
            else null

            val bitmapPath = MediaStore.Images.Media.insertImage(
                context.contentResolver,
                bitmapImage,
                "Character", null
            )

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    context.getString(R.string.share_character_message_title) + "\n"
                            + character.name + "\n" + character.description)
                putExtra(
                    Intent.EXTRA_STREAM,
                    Uri.parse(bitmapPath)
                )
                type = "image/*"
            }
            val shareIntent = Intent.createChooser(
                sendIntent,
                context.getString(R.string.share_character_title)
            )
            ContextCompat.startActivity(context, shareIntent, null)
        } catch (e: Exception) {
            Toast.makeText(
                context,
                context.getString(R.string.error_on_sending_intent),
                Toast.LENGTH_SHORT
            ).show()
        } finally {
            if (viewModel is CharactersViewModel)
                viewModel.onCharacterShared()
            else if (viewModel is SearchCharactersViewModel)
                viewModel.onFoundCharacterShared()
        }
    }
}
