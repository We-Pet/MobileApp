package com.ipca.wepet.domain.model

import com.google.gson.annotations.SerializedName

data class EventModel(
    @SerializedName("_id")
    val id: String?
)

data class EventResponse(
    @SerializedName("data")
    val data: List<EventModel>
)