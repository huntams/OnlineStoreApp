package com.example.onlinestoreapp.presentation.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.onlinestoreapp.R
import com.example.ui.databinding.ProductRatingViewBinding

class ProductRatingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: ProductRatingViewBinding

    init {
        binding = ProductRatingViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    fun setRating(rating: Float, count: Int) {
        binding.textViewRating.text = rating.toString()
        binding.textViewCounter.text = resources.getString(R.string.counter, count)
        setStarIcon(rating)
    }

    private fun setStarIcon(rating: Float) {
        if (rating >= 1) {
            binding.textViewRating.visibility = View.VISIBLE
            binding.textViewRating.visibility = View.VISIBLE
        } else {
            binding.textViewRating.visibility = View.GONE
            binding.textViewCounter.visibility = View.GONE
        }
    }
}