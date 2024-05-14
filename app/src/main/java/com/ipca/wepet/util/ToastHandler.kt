package com.ipca.wepet.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

object ToastHandler {
    fun showToast(context: Context, @StringRes errorMessageId: Int) {
        Toast.makeText(context, context.getString(errorMessageId), Toast.LENGTH_SHORT).show()
    }
}