package com.ipca.wepet.data.mapper.shelter

import com.ipca.wepet.data.local.shelter.ShelterEntity
import com.ipca.wepet.domain.model.ShelterModel

fun ShelterEntity.toShelterModel(): ShelterModel {
    return ShelterModel(
        id = id,
        name = name ?: "",
        description = description ?: "",
        email = email ?: "",
        birthDate = birthDate ?: String(),
        phoneNumber = phoneNumber ?: "",
        country = country ?: "",
        city = city ?: "",
        postalCode = postalCode ?: "",
        address = address ?: "",
        isVerified = isVerified ?: false,
        createdAt = createdAt ?: "",
        userId = userId ?: String(),
        deleted = deleted
    )
}

fun ShelterModel.toShelterEntity(): ShelterEntity {
    return ShelterEntity(
        id = id ?: "",
        name = name ?: "",
        description = description ?: "",
        email = email ?: "",
        birthDate = birthDate ?: String(),
        phoneNumber = phoneNumber ?: "",
        country = country ?: "",
        city = city ?: "",
        postalCode = postalCode ?: "",
        address = address ?: "",
        isVerified = isVerified ?: false,
        createdAt = createdAt,
        userId = userId ?: String(),
        deleted = deleted ?: 0
    )
}
