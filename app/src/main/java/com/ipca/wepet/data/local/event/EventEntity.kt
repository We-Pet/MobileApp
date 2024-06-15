package com.ipca.wepet.data.local.event

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventEntity(
    @PrimaryKey val id: String
)
