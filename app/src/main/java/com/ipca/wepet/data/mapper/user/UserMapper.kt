package com.ipca.wepet.data.mapper.user

import com.ipca.wepet.data.local.user.UserEntity
import com.ipca.wepet.domain.model.UserModel

fun UserEntity.toUserModel(): UserModel {
    return UserModel(
        id = id.toString(),
        name = name,
        email = email,
        city = city,
        deleted = deleted,
        birthDate = birthDate,
        phoneNumber = phoneNumber,
        updatedAt = updatedAt,
        createdAt = createdAt
    )
}

fun UserModel.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        email = email,
        city = city,
        deleted = deleted,
        birthDate = birthDate,
        phoneNumber = phoneNumber,
        updatedAt = updatedAt,
        createdAt = createdAt
    )
}