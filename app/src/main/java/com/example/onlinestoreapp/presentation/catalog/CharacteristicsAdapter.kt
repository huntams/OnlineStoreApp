package com.example.onlinestoreapp.presentation.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Info
import com.example.onlinestoreapp.databinding.ItemCharacteristicsBinding
import javax.inject.Inject

class CharacteristicsAdapter @Inject constructor() :
    ListAdapter<Info, CharacteristicsAdapter.ViewHolder>(
        diffUtilCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCharacteristicsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemCharacteristicsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Info) {
            with(binding) {
                textViewName.text = item.title
                textViewInfo.text = item.value
            }
        }
    }
}

private val diffUtilCallback = object : DiffUtil.ItemCallback<Info>() {

    override fun areContentsTheSame(oldItem: Info, newItem: Info): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Info, newItem: Info): Boolean {
        return oldItem.title == newItem.title
    }
}