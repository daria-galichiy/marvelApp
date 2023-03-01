package ru.alfacampus.featureCreatedCharacters.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.alfacampus.featureCreatedCharacters.databinding.ItemCreatedCharacterBinding
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CreatedCharacterEntity

class CreatedCharactersAdapter : ListAdapter<CreatedCharacterEntity, CreatedCharactersViewHolder>(DiffCallback) {

    private lateinit var onItemClickListener: ((CreatedCharacterEntity) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatedCharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCreatedCharacterBinding.inflate(inflater, parent, false)
        return CreatedCharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CreatedCharactersViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener(character)
            }
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<CreatedCharacterEntity>() {

        override fun areItemsTheSame(oldItem: CreatedCharacterEntity, newItem: CreatedCharacterEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CreatedCharacterEntity, newItem: CreatedCharacterEntity): Boolean =
            oldItem == newItem
    }

    fun setOnItemClickListener(listener: (CreatedCharacterEntity) -> Unit) {
        onItemClickListener = listener
    }
}
