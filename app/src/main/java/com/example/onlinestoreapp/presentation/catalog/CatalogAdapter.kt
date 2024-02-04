package com.example.onlinestoreapp.presentation.catalog


import android.content.res.Resources
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.data.remote.model.ApiProduct
import com.example.onlinestoreapp.databinding.ItemCatalogBinding
import com.example.onlinestoreapp.domain.ImagesUseCase
import com.example.onlinestoreapp.presentation.ImagePagerAdapter
import javax.inject.Inject

class CatalogAdapter @Inject constructor(
    private val imagesUseCase: ImagesUseCase,
    private val resources: Resources
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
        private val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(8,0,8,0)
        }

        fun bind(item: ApiProduct) {
            with(binding) {
                textViewPrice.text = "${item.price.price} ${item.price.unit}"
                textViewPrice.paintFlags = textViewPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                textViewTitle.text = item.title
                textViewSubtitle.text = item.subtitle
                val imagePagerAdapter = ImagePagerAdapter()
                val data =imagesUseCase(item.id)
                imagePagerAdapter.submitList(data)
                imageSlider.viewpager2.adapter = imagePagerAdapter
                val dotsImage = Array(2) { ImageView(binding.root.context) }
                dotsImage.forEach {
                    it.setImageResource(
                        R.drawable.ic_page_small_default_8
                    )
                    imageSlider.slideDotLL.addView(it,params)
                }
                dotsImage[0].setImageResource(R.drawable.ic_page_small_8)

                val pageChangeListener = object : ViewPager2.OnPageChangeCallback(){
                    override fun onPageSelected(position: Int) {
                        dotsImage.mapIndexed { index, imageView ->
                            if (position == index){
                                imageView.setImageResource(
                                    R.drawable.ic_page_small_8
                                )
                            }else{
                                imageView.setImageResource(R.drawable.ic_page_small_default_8)
                            }
                        }
                        super.onPageSelected(position)
                    }
                }
                imageSlider.viewpager2.registerOnPageChangeCallback(pageChangeListener)
                if(item.like)
                    buttonLiked.setIconResource(R.drawable.ic_heart_active_24)
                else
                    buttonLiked.setIconResource(R.drawable.ic_heart_default_24)
                stars.setRating(item.feedback.rating, item.feedback.count)
                cardPrice.textViewDiscount.text = resources.getString(R.string.discount_procent,item.price.discount)
                textViewPriceWithDiscount.text = "${item.price.priceWithDiscount} ${item.price.unit}"
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