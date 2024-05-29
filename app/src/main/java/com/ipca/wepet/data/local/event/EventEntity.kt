package com.ipca.wepet.data.local.event

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventEntity(
    @PrimaryKey @NonNull val id: String
)
