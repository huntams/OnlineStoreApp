package com.example.onlinestoreapp.domain

import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.data.model.Image
import javax.inject.Inject

class ImagesUseCase @Inject constructor(
) {

    private val listImage = listOf(
        Image(id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50", R.drawable.blade),
        Image(id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50", R.drawable.gel),
        Image(id = "54a876a5-2205-48ba-9498-cfecff4baa6e", R.drawable.soap),
        Image(id = "54a876a5-2205-48ba-9498-cfecff4baa6e", R.drawable.big_soap),
        Image(id = "75c84407-52e1-4cce-a73a-ff2d3ac031b3", R.drawable.gel),
        Image(id = "75c84407-52e1-4cce-a73a-ff2d3ac031b3", R.drawable.blade) ,
        Image(id = "16f88865-ae74-4b7c-9d85-b68334bb97db", R.drawable.hard_soap),
        Image(id = "16f88865-ae74-4b7c-9d85-b68334bb97db", R.drawable.mask),
        Image(id = "26f88856-ae74-4b7c-9d85-b68334bb97db", R.drawable.big_soap),
        Image(id = "26f88856-ae74-4b7c-9d85-b68334bb97db", R.drawable.hard_soap),
        Image(id = "15f88865-ae74-4b7c-9d81-b78334bb97db", R.drawable.blade),
        Image(id = "15f88865-ae74-4b7c-9d81-b78334bb97db", R.drawable.soap),
        Image(id = "88f88865-ae74-4b7c-9d81-b78334bb97db", R.drawable.mask),
        Image(id = "88f88865-ae74-4b7c-9d81-b78334bb97db", R.drawable.hard_soap),
        Image(id = "55f58865-ae74-4b7c-9d81-b78334bb97db", R.drawable.soap),
        Image(id = "55f58865-ae74-4b7c-9d81-b78334bb97db", R.drawable.gel),
    )
    operator fun invoke(id: String) : List<Image> {
        return listImage.filter { it.id == id }
    }
}