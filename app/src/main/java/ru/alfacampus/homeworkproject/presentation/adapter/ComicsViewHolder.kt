package ru.alfacampus.homeworkproject.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.alfacampus.homeworkproject.R
import ru.alfacampus.homeworkproject.data.dto.comics.Result
import ru.alfacampus.homeworkproject.databinding.ItemComicsBinding

class ComicsViewHolder(
    private val binding: ItemComicsBinding
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var comics: Result

    fun bind(comics: Result) {
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
