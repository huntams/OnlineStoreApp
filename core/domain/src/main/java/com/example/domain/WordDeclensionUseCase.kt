package com.example.domain

import javax.inject.Inject
import kotlin.math.abs

class WordDeclensionUseCase @Inject constructor(
) {

    operator fun invoke(num: Int, list: List<String>): String {
        val number = (abs(num) % 100)
        val numberDec = number % 10
        if (numberDec == 1)
            return list[0]
        else if (numberDec in 2..4)
            return list[1]
        return list[2]

    }
}