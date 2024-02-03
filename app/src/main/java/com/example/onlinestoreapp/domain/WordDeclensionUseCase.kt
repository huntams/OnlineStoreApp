package com.example.onlinestoreapp.domain

import android.content.res.Resources
import com.example.onlinestoreapp.R
import javax.inject.Inject
import kotlin.math.abs

class WordDeclensionUseCase @Inject constructor(
    resources: Resources
) {
    private val review = listOf(
        resources.getString(R.string.review),
        resources.getString(R.string.reviews),
        resources.getString(R.string.reviewes)
    )
    private val thing = listOf(
        resources.getString(R.string.thing),
        resources.getString(R.string.things),
        resources.getString(R.string.thingses)
    )

    operator fun invoke(num: Int,  data: String): String {

        val list: List<String> = if (review.contains(data))
            review
        else
            thing
        val number = (abs(num) % 100)
        val numberDec = number % 10
        if (numberDec == 1)
            return list[0]
        else if (numberDec in 2..4)
            return list[1]
        return list[2]

    }
}