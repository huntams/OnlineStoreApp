package com.example.onlinestoreapp.data.mappers

import com.example.onlinestoreapp.data.db.model.InfoEntity
import com.example.onlinestoreapp.data.db.model.ProductEntity
import com.example.onlinestoreapp.data.db.model.ProductWithInfoEntity
import com.example.onlinestoreapp.data.remote.model.ApiFeedback
import com.example.onlinestoreapp.data.remote.model.ApiInfo
import com.example.onlinestoreapp.data.remote.model.ApiPrice
import com.example.onlinestoreapp.data.remote.model.ApiProduct
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductMapper @Inject constructor() {
    fun fromEntityToUIModel(entity: ProductWithInfoEntity): ApiProduct {
        with(entity){
            return ApiProduct(
                id = productEntity.id,
            available = productEntity.available,
            title = productEntity.title,
            subtitle = productEntity.subtitle,
            description = productEntity.description,
            feedback = ApiFeedback(
                count = productEntity.count,
                rating = productEntity.rating
            ),
            info = info.map {
                ApiInfo(title = it.title, value = it.value)
            },
            ingredients = productEntity.ingredients,
            price = ApiPrice(
                price = productEntity.price,
                discount = productEntity.discount,
                priceWithDiscount = productEntity.priceWithDiscount,
                unit = productEntity.unit
            ),
            tags = productEntity.tags
            )

        }

    }

    fun fromUIModelToEntity(product: ApiProduct): ProductEntity {

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
            unit = product.price.unit
        )
    }
    fun fromInfoUIModelToEntity(product: ApiProduct):List<InfoEntity>{
        return product.info.map{ InfoEntity(
            productId = product.id, title = it.title, value = it.value
        )
        }
    }
}