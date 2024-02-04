package com.example.onlinestoreapp.base

import android.widget.LinearLayout
import javax.inject.Inject

class ViewPagerCallback @Inject constructor(

) {

    fun paramsDots() = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(8,0,8,0)
    }
}