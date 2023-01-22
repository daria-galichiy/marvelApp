package ru.alfacampus.homeworkproject.presentation.adapter

import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.alfacampus.homeworkproject.R
import ru.alfacampus.homeworkproject.databinding.ItemCharacterBinding
import ru.alfacampus.homeworkproject.data.dto.character.CharacterMarvel


class CharactersViewHolder(
    private val binding: ItemCharacterBinding,
    listener: CharacterInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var character: CharacterMarvel

    private val popupMenu by lazy {
        PopupMenu(itemView.context, binding.options).apply {
            inflate(R.menu.options_character)
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.remove -> {
                        listener.onRemoveClicked(character)
                        true
                    }
                    R.id.addToFavorites -> {
                        listener.onAddToFavoritesClicked(character)
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

    fun bind(character: CharacterMarvel) {
        this.character = character

        with(binding) {
            characterName.text = character.name
            characterDescription.text = character.description
            Glide.with(characterAvatar.context)
                .load(character.thumbnail.path
                        + "/standard_fantastic."
                        + character.thumbnail.extension)
                .error(R.drawable.agent_mobius_error)
                .into(characterAvatar)
            characterAvatar.clipToOutline = true
        }
    }
}
