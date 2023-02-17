package ru.alfacampus.homeworkproject.featureCharactersDescription.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.alfacampus.homeworkproject.coreData.data.entities.comics.ResultEntity
import ru.alfacampus.homeworkproject.featureCharactersDescription.databinding.ItemComicsBinding
import ru.alfacampus.homeworkproject.resources.R


class ComicsViewHolder (
    private val binding: ItemComicsBinding
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var comics: ResultEntity

    fun bind(comics: ResultEntity) {
        this.comics = comics

        with(binding) {
            comicsName.text = comics.title
            Glide.with(comicsImage.context)
                .load(comics.thumbnail.path
                        + "/portrait_xlarge."
                        + comics.thumbnail.extension)
                .error(R.drawable.comics_error)
                .into(comicsImage)
            comicsImage.clipToOutline = true
        }
    }
}
