package com.example.data.mappers

import com.example.data.R
import com.example.database.model.InfoEntity
import com.example.database.model.ProductEntity
import com.example.database.model.ProductWithInfoEntity
import com.example.model.Feedback
import com.example.model.Image
import com.example.model.Info
import com.example.model.Price
import com.example.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductDBMapper @Inject constructor() {
    fun fromEntityToUIModel(entity: ProductWithInfoEntity): Product {
        with(entity) {
            return Product(
                id = productEntity.id,
                available = productEntity.available,
                title = productEntity.title,
                subtitle = productEntity.subtitle,
                description = productEntity.description,
                feedback = Feedback(
                    count = productEntity.count,
                    rating = productEntity.rating
                ),
                info = info.map {
                    Info(title = it.title, value = it.value)
                },
                ingredients = productEntity.ingredients,
                price = Price(
                    price = productEntity.price,
                    discount = productEntity.discount,
                    priceWithDiscount = productEntity.priceWithDiscount,
                    unit = productEntity.unit
                ),
                images = listImage.filter { it.id == productEntity.id },
                tags = productEntity.tags
            )
        }

    }

    fun fromUIModelToEntity(product: Product): ProductEntity {

        return ProductEntity(
            id = product.id,
            available = product.available,
            title = product.title,
            subtitle = product.subtitle,
            description = product.description,
            ingredients = product.ingredients,
            price = product.price.price,
            tags = product.tags,
            count = product.feedback.count,
            discount = product.price.discount,
            priceWithDiscount = product.price.priceWithDiscount,
            rating = product.feedback.rating,
            unit = product.price.unit,
            like = product.like
        )
    }

    fun fromInfoUIModelToEntity(product: Product): List<InfoEntity> {
        return product.info.map {
            InfoEntity(
                productId = product.id, title = it.title, value = it.value
            )
        }
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