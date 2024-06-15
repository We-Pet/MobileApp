package com.ipca.wepet.domain.model

data class SettingModel(
    val id: Int,
    val name: String,
    val description: String,
    var isEnabled: Boolean
)
