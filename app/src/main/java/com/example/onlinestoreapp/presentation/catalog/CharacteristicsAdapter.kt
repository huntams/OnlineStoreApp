package com.example.onlinestoreapp.presentation.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestoreapp.data.remote.model.ApiInfo
import com.example.onlinestoreapp.databinding.ItemCharacteristicsBinding
import javax.inject.Inject

class CharacteristicsAdapter @Inject constructor() :
    ListAdapter<ApiInfo, CharacteristicsAdapter.ViewHolder>(
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
        fun bind(item: ApiInfo) {
            with(binding) {
                textViewName.text = item.title
                textViewInfo.text = item.value
            }
        }
    }
}

private val diffUtilCallback = object : DiffUtil.ItemCallback<ApiInfo>() {

    override fun areContentsTheSame(oldItem: ApiInfo, newItem: ApiInfo): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: ApiInfo, newItem: ApiInfo): Boolean {
        return oldItem.title == newItem.title
    }
}