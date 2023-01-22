package ru.alfacampus.homeworkproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.alfacampus.homeworkproject.data.dto.comics.Result
import ru.alfacampus.homeworkproject.databinding.ItemComicsBinding


class ComicsAdapter
    : ListAdapter<Result, ComicsViewHolder>(DiffCallback) {

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

    private object DiffCallback : DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem == newItem
    }
}
