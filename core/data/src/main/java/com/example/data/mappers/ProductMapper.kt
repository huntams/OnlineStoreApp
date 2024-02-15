package com.example.data.mappers

import com.example.data.R
import com.example.model.Feedback
import com.example.model.Image
import com.example.model.Info
import com.example.model.Price
import com.example.model.Product
import com.example.network.remote.model.ApiFeedback
import com.example.network.remote.model.ApiInfo
import com.example.network.remote.model.ApiPrice
import com.example.network.remote.model.ApiProduct
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductMapper @Inject constructor() {
    fun fromEntityToUIModel(entity: Product): ApiProduct {
        with(entity) {
            return ApiProduct(
                id = entity.id,
                available = entity.available,
                title = entity.title,
                subtitle = entity.subtitle,
                description = entity.description,
                ingredients = entity.ingredients,
                price = ApiPrice(
                    price = entity.price.price,
                    discount = entity.price.discount,
                    priceWithDiscount = entity.price.priceWithDiscount,
                    unit = entity.price.unit,
                ),
                tags = entity.tags,
                like = entity.like,
                feedback = ApiFeedback(
                    count = entity.feedback.count,
                    rating = entity.feedback.rating
                ),
                info = entity.info.map {
                    ApiInfo(
                        title = it.title,
                        value = it.value
                    )
                }
            )

        }

    }

    fun fromUIModelToEntity(product: ApiProduct): Product {

        return Product(
            id = product.id,
            available = product.available,
            title = product.title,
            subtitle = product.subtitle,
            description = product.description,
            ingredients = product.ingredients,
            price = Price(
                price = product.price.price,
                discount = product.price.discount,
                priceWithDiscount = product.price.priceWithDiscount,
                unit = product.price.unit,
            ),
            tags = product.tags,
            like = product.like,
            feedback = Feedback(
                count = product.feedback.count,
                rating = product.feedback.rating
            ),
            images = listImage.filter { it.id == product.id },
            info = product.info.map {
                Info(
                    title = it.title,
                    value = it.value
                )
            }
        )
    }

    private val listImage = listOf(
        Image(id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50", R.drawable.blade),
        Image(id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50", R.drawable.gel),
        Image(id = "54a876a5-2205-48ba-9498-cfecff4baa6e", R.drawable.soap),
        Image(id = "54a876a5-2205-48ba-9498-cfecff4baa6e", R.drawable.big_soap),
        Image(id = "75c84407-52e1-4cce-a73a-ff2d3ac031b3", R.drawable.gel),
        Image(id = "75c84407-52e1-4cce-a73a-ff2d3ac031b3", R.drawable.blade),
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
}