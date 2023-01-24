package ru.alfacampus.homeworkproject.presentation.adapter

import android.content.Context
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.NonDisposableHandle.parent
import ru.alfacampus.homeworkproject.R
import ru.alfacampus.homeworkproject.databinding.ItemCharacterBinding
import ru.alfacampus.homeworkproject.data.dto.character.CharacterMarvel


class CharactersViewHolder(
    private val binding: ItemCharacterBinding,
    listener: CharacterInteractionListener,
    context: Context
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var character: CharacterMarvel

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

    fun bind(character: CharacterMarvel, isFavoriteCharacter: Boolean) {
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

            if (isFavoriteCharacter)
                options.visibility = View.GONE
            else
                options.visibility = View.VISIBLE
        }
    }
}
