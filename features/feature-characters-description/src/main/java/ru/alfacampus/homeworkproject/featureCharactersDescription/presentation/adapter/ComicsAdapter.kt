package ru.alfacampus.homeworkproject.featureCharactersDescription.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.alfacampus.homeworkproject.coreData.data.entities.comics.ResultEntity
import ru.alfacampus.homeworkproject.featureCharactersDescription.databinding.ItemComicsBinding


class ComicsAdapter : ListAdapter<ResultEntity, ComicsViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemComicsBinding.inflate(
            inflater, parent, false
        )
        return ComicsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val comics = getItem(position)
        holder.bind(comics)
    }

    private object DiffCallback : DiffUtil.ItemCallback<ResultEntity>() {

        override fun areItemsTheSame(oldItem: ResultEntity, newItem: ResultEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ResultEntity, newItem: ResultEntity): Boolean =
            oldItem == newItem
    }
}
