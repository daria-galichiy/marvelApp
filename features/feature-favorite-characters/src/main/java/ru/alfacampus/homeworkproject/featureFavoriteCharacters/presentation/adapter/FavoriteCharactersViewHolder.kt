package ru.alfacampus.homeworkproject.featureFavoriteCharacters.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.alfacampus.homeworkproject.coreData.data.entities.character.CharacterMarvelEntity
import ru.alfacampus.homeworkproject.featureFavoriteCharacters.databinding.ItemFavoriteCharacterBinding
import ru.alfacampus.homeworkproject.resources.R


class FavoriteCharactersViewHolder(
    private val binding: ItemFavoriteCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var character: CharacterMarvelEntity

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
