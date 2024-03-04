package com.example.product

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.product.databinding.ButtonWithPriceBinding

class ButtonWithPrice @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: ButtonWithPriceBinding

    init {
        binding = ButtonWithPriceBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    fun setPrice(price: String) {
        binding.textViewPrice.text = price
    }

    fun setOldPrice(price: String) {
        binding.textViewOldPrice.text = price
        binding.textViewOldPrice.paintFlags =
            binding.textViewOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }


}