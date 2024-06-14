package com.ipca.wepet.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

fun <T> Flow<T>.asLiveData(lifecycleOwner: LifecycleOwner): LiveData<T> = liveData {
    lifecycleOwner.lifecycleScope.launch {
        this@asLiveData
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .distinctUntilChanged()
            .collect { value -> emit(value) }
    }
}

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) {
    observe(lifecycleOwner, Observer { observer(it) })
}