package ru.alfacampus.homeworkproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.alfacampus.homeworkproject.databinding.ItemCharacterBinding
import ru.alfacampus.homeworkproject.data.dto.CharacterMarvel


class CharactersAdapter(
    private val interactionListener: CharacterInteractionListener
) : ListAdapter<CharacterMarvel, CharactersViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(
            inflater, parent, false
        )
        return CharactersViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    private object DiffCallback : DiffUtil.ItemCallback<CharacterMarvel>() {

        override fun areItemsTheSame(oldItem: CharacterMarvel, newItem: CharacterMarvel): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CharacterMarvel, newItem: CharacterMarvel): Boolean =
            oldItem == newItem
    }
}
