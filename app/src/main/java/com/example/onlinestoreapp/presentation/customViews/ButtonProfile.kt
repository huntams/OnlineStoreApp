package com.example.onlinestoreapp.presentation.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.databinding.CustomProfileButtonBinding
import com.google.android.material.card.MaterialCardView

class ButtonProfile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private var binding: CustomProfileButtonBinding

    init {
        binding = CustomProfileButtonBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
        context.withStyledAttributes(
            attrs,
            R.styleable.ProfileButton, defStyleAttr, 0
        ) {
            val startImage =
                getDrawable(R.styleable.ProfileButton_startIconDrawable)?.let { image ->
                    binding.imageViewStart.setImageDrawable(image)
                }
            val endImage = getDrawable(R.styleable.ProfileButton_endIconDrawable)?.let { image ->
                binding.imageViewEnd.setImageDrawable(image)
            }
        }
    }

    fun setCenter(text: String) {
        binding.textViewCenter.text = text
        binding.textViewTop.visibility = View.GONE
        binding.textViewBottom.visibility = View.GONE
    }

    fun setAllText(topText: String, bottomText: String) {
        binding.textViewCenter.visibility = View.GONE
        binding.textViewBottom.text = bottomText
        binding.textViewTop.text = topText
    }

    fun setStartImage(@DrawableRes imagesRes: Int) {
        binding.imageViewStart.setImageDrawable(
            ContextCompat.getDrawable(context, imagesRes)
        )
    }

    fun setEndImage(@DrawableRes imagesRes: Int) {
        binding.imageViewEnd.setImageDrawable(
            ContextCompat.getDrawable(context, imagesRes)
        )
    }
}