package com.example.onlinestoreapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Image
import com.example.ui.databinding.ItemImageBinding
import javax.inject.Inject

class ImagePagerAdapter @Inject constructor(
) : ListAdapter<Image, ImagePagerAdapter.ViewHolder>(
    diffUtilCallback
) {

    private var onClick: (Image) -> Unit = {}
    fun setCallback(callback: (Image) -> Unit) {
        this.onClick = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Image) {
            with(binding) {
                imageViewProduct.setImageResource(item.image)
                root.setOnClickListener {
                    onClick(item)
                }
            }
        }
    }
}

private val diffUtilCallback = object : DiffUtil.ItemCallback<Image>() {

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }
}