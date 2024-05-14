package com.ipca.wepet.data.mapper.user

import com.ipca.wepet.data.local.animal.AnimalEntity
import com.ipca.wepet.domain.model.AnimalModel
import java.util.Date

fun AnimalEntity.toAnimalModel(): AnimalModel {
    return AnimalModel(
        id = id,
        name = name ?: "",
        birthDate = birthDate ?: String(),
        description = description ?: "",
        gender = gender ?: "",
        userId = userId ?: "",
        shelterId = shelterId ?: "",
        city = city ?: "",
        size = size ?: "",
        animalType = animalType ?: "",
        breed = breed ?: "",
        createdAt = createdAt ?: String(),
        deleted = deleted ?: 0
    )
}

fun AnimalModel.toAnimalEntity(): AnimalEntity {
    return AnimalEntity(
        id = id ?: "",
        name = name ?: "",
        birthDate = birthDate?: String(),
        description = description ?: "",
        gender = gender ?: "",
        userId = userId ?: "",
        shelterId = shelterId ?: "",
        city = city ?: "",
        size = size ?: "",
        animalType = animalType ?: "",
        breed = breed ?: "",
        createdAt = createdAt?: String(),
        deleted = deleted ?: 0
    )
}

