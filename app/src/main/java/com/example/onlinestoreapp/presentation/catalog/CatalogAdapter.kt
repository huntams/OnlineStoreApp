package com.example.onlinestoreapp.presentation.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.data.remote.model.ApiProduct
import com.example.onlinestoreapp.databinding.ItemCatalogBinding
import javax.inject.Inject

class CatalogAdapter @Inject constructor() : ListAdapter<ApiProduct, CatalogAdapter.ViewHolder>(
    diffUtilCallback
) {
    private var onClick: (ApiProduct) -> Unit = {}
    fun setCallback(callback: (ApiProduct) -> Unit) {
        this.onClick = callback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemCatalogBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ApiProduct) {
            with(binding) {
                textViewPrice.text = item.price.price
                textViewTitle.text = item.title
                textViewSubtitle.text = item.subtitle
                textViewPriceWithDiscount.text = item.price.priceWithDiscount
                imageViewProduct.setImageDrawable( R.drawable.ic_heart_active_24.toDrawable())
                root.setOnClickListener {
                    onClick(item)
                }
            }
        }
    }
}

private val diffUtilCallback = object : DiffUtil.ItemCallback<ApiProduct>() {

    override fun areContentsTheSame(oldItem: ApiProduct, newItem: ApiProduct): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: ApiProduct, newItem: ApiProduct): Boolean {
        return oldItem.id == newItem.id
    }
}