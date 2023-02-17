package ru.alfacampus.homeworkproject.featureCharacters.presentation.adapter

import android.content.Context
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import ru.alfacampus.homeworkproject.characters.databinding.ItemCharacterBinding
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.resources.R


class CharactersViewHolder(
    private val binding: ItemCharacterBinding,
    listener: CharacterInteractionListener,
    context: Context
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var character: CharacterMarvelEntity

    private val popupMenu by lazy {
        PopupMenu(itemView.context, binding.options).apply {
            inflate(R.menu.options_character)
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.hide -> {
                        // TODO: realize hiding of character card
                        true
                    }
                    R.id.addToFavorites -> {
                        listener.onAddCharacterToFavoritesClicked(character)
                        Snackbar.make(
                            binding.root,
                            context.resources.getString(R.string.character_was_added_to_favorites),
                            Snackbar.LENGTH_SHORT
                        )
                        true
                    }
                    else -> false
                }
            }
        }
    }

    init {
        binding.options.setOnClickListener {
            popupMenu.show()
        }
    }

    fun bind(character: CharacterMarvelEntity) {
        this.character = character

        with(binding) {
            characterName.text = character.name
            characterDescription.text = character.description
            Glide.with(characterAvatar.context)
                .load(
                    character.thumbnail.path
                            + "/standard_fantastic."
                            + character.thumbnail.extension
                )
                .error(R.drawable.agent_mobius_error)
                .into(characterAvatar)
            characterAvatar.clipToOutline = true
        }
    }
}
