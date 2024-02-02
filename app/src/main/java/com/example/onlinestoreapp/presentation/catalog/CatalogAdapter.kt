package com.example.onlinestoreapp.presentation.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestoreapp.data.remote.model.ApiProduct
import com.example.onlinestoreapp.databinding.ItemCatalogBinding
import com.example.onlinestoreapp.domain.ImagesUseCase
import com.example.onlinestoreapp.presentation.ImagePagerAdapter
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy
import javax.inject.Inject

class CatalogAdapter @Inject constructor(
    private val imagesUseCase: ImagesUseCase
) : ListAdapter<ApiProduct, CatalogAdapter.ViewHolder>(
    diffUtilCallback
) {

    private var onClick: (ApiProduct) -> Unit = {}
    fun setCallback(callback: (ApiProduct) -> Unit) {
        this.onClick = callback
    }

    private var onClickLike: (ApiProduct) -> Unit = {}
    fun setLike(callback: (ApiProduct) -> Unit) {
        this.onClickLike = callback
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
                recyclerViewImages.layoutManager = CarouselLayoutManager(
                    FullScreenCarouselStrategy()
                )
                val snapHelper = CarouselSnapHelper()
                snapHelper.attachToRecyclerView(recyclerViewImages)
                val imagePagerAdapter = ImagePagerAdapter()
                imagePagerAdapter.submitList(imagesUseCase(item.id))
                recyclerViewImages.adapter = imagePagerAdapter
                textViewPrice.text = item.price.price
                textViewTitle.text = item.title
                textViewSubtitle.text = item.subtitle
                stars.setRating(item.feedback.rating, item.feedback.count)
                textViewPriceWithDiscount.text = item.price.priceWithDiscount
                imagePagerAdapter.setCallback {
                    onClick(item)
                }
                buttonLiked.setOnClickListener {
                    onClickLike(item)
                }
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