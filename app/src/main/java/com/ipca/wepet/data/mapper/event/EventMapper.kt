package com.ipca.wepet.data.mapper.event

import com.ipca.wepet.data.local.event.EventEntity
import com.ipca.wepet.domain.model.EventModel

fun EventEntity.toEventModel(): EventModel {
    return EventModel(
        id = id
    )
}

fun EventModel.toEventEntity(): EventEntity {
    return EventEntity(
        id = id ?: ""
    )
}
