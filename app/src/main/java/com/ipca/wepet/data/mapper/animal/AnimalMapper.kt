package com.ipca.wepet.data.mapper.animal

import com.ipca.wepet.data.local.animal.AnimalEntity
import com.ipca.wepet.domain.model.AnimalModel

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
        deleted = deleted
    )
}

fun AnimalModel.toAnimalEntity(): AnimalEntity {
    return AnimalEntity(
        id = id ?: "",
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

