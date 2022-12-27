package ru.alfacampus.homeworkproject.adapter

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.alfacampus.homeworkproject.R
import ru.alfacampus.homeworkproject.databinding.ItemCharacterBinding
import ru.alfacampus.homeworkproject.dto.CharacterMarvel


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
            characterAvatar.setImageResource(R.drawable.iron_man_sample)
        }
    }
}
