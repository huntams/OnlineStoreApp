package com.example.onlinestoreapp.domain

import javax.inject.Inject
import kotlin.math.abs

class WordDeclensionUseCase @Inject constructor() {
    operator fun invoke(num: Int, data: Array<String>): String {
        return when (abs(num) % 100) {
            in 11..19 -> {
                data[2]
            }
            in 2..4 -> {
                data[1]
            }
            1 -> {
                data[0]
            }
            else -> {
                data[2]
            }
        }
    }
}