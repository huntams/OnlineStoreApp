package com.example.data.mappers

import com.example.database.model.UserEntity
import com.example.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserMapper @Inject constructor() {

    fun fromEntityToUIModel(entity: UserEntity): User {
        return User(
            name = entity.name,
            surname = entity.surname,
            phone = entity.phone
        )
    }

    fun fromUIModelToEntity(user: User): UserEntity {
        return UserEntity(
            name = user.name,
            surname = user.surname,
            phone = user.phone
        )
    }
}