package com.katsiro.alexey.gia.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val job = Job()

    protected val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun <T> loadData(liveData: MutableLiveData<T>, block: suspend () -> T) {
        scope.launch {
            val result = block()
            liveData.value = result
        }
    }
}