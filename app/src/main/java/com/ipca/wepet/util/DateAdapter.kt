package com.ipca.wepet.util

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Date

class DateAdapter {
    @ToJson
    fun toJson(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return format.format(date)
    }

    @FromJson
    fun fromJson(dateString: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return format.parse(dateString)!!
    }
}