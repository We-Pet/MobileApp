package com.ipca.wepet.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EventModel(
    @SerializedName("_id")
    val id: String?
) : Serializable

data class EventResponse(
    @SerializedName("data")
    val data: List<EventModel>
)