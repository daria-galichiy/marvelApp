package ru.alfacampus.featureCreatedCharacters.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.alfacampus.featureCreatedCharacters.databinding.ItemCreatedCharacterBinding
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CreatedCharacterEntity
import ru.alfacampus.homeworkproject.resources.R

class CreatedCharactersViewHolder(
    private val binding: ItemCreatedCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var character: CreatedCharacterEntity

    fun bind(character: CreatedCharacterEntity) {
        this.character = character

        with(binding) {
            characterName.text = character.name
            characterDescription.text = character.description
            Glide.with(characterAvatar.context)
                .load(character.thumbnailUri)
                .error(R.drawable.agent_mobius_error)
                .into(characterAvatar)
            characterAvatar.clipToOutline = true
        }
    }
}
