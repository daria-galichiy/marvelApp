package ru.alfacampus.homeworkproject.featureCharacters.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.alfacampus.homeworkproject.characters.databinding.ItemCharacterBinding
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity


class CharactersAdapter(
    private val interactionListener: CharacterInteractionListener
) : ListAdapter<CharacterMarvelEntity, CharactersViewHolder>(DiffCallback) {

    private lateinit var onItemClickListener: ((CharacterMarvelEntity) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(
            inflater, parent, false
        )
        return CharactersViewHolder(binding, interactionListener, parent.context)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener(character)
            }
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<CharacterMarvelEntity>() {

        override fun areItemsTheSame(oldItem: CharacterMarvelEntity, newItem: CharacterMarvelEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CharacterMarvelEntity, newItem: CharacterMarvelEntity): Boolean =
            oldItem == newItem
    }

    fun setOnItemClickListener(listener: (CharacterMarvelEntity) -> Unit) {
        onItemClickListener = listener
    }
}
