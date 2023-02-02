package ru.alfacampus.homeworkproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.alfacampus.homeworkproject.data.dto.character.CharacterMarvel
import ru.alfacampus.homeworkproject.databinding.ItemCharacterBinding


class CharactersAdapter(
    private val interactionListener: CharacterInteractionListener,
    private val isFavoriteCharacter: Boolean
) : ListAdapter<CharacterMarvel, CharactersViewHolder>(DiffCallback) {

    private lateinit var onItemClickListener: ((CharacterMarvel) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(
            inflater, parent, false
        )
        return CharactersViewHolder(binding, interactionListener, parent.context)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character, isFavoriteCharacter)
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener(character)
            }
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<CharacterMarvel>() {

        override fun areItemsTheSame(oldItem: CharacterMarvel, newItem: CharacterMarvel): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CharacterMarvel, newItem: CharacterMarvel): Boolean =
            oldItem == newItem
    }

    fun setOnItemClickListener(listener: (CharacterMarvel) -> Unit) {
        onItemClickListener = listener
    }
}
