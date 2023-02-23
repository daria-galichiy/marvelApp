package ru.alfacampus.homeworkproject.featureFavoriteCharacters.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.databinding.ItemFavoriteCharacterBinding


class FavoriteCharactersAdapter() : ListAdapter<CharacterMarvelEntity, FavoriteCharactersViewHolder>(DiffCallback) {

    private lateinit var onItemClickListener: ((CharacterMarvelEntity) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoriteCharacterBinding.inflate(
            inflater, parent, false
        )
        return FavoriteCharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteCharactersViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener(character)
            }
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<CharacterMarvelEntity>() {

        override fun areItemsTheSame(
            oldItem: CharacterMarvelEntity,
            newItem: CharacterMarvelEntity
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CharacterMarvelEntity,
            newItem: CharacterMarvelEntity
        ): Boolean =
            oldItem == newItem
    }

    fun setOnItemClickListener(listener: (CharacterMarvelEntity) -> Unit) {
        onItemClickListener = listener
    }
}
