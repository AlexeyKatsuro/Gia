package com.katsiro.alexey.gia.ui.picker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.katsiro.alexey.gia.base.BaseViewModel
import com.katsiro.alexey.gia.data.Picked
import com.katsiro.alexey.gia.utils.Event

class TextPickDialogViewModel : BaseViewModel() {

    private val _onItemClicked = MutableLiveData<Event<Picked<Any>>>()
    val onItemClicked: LiveData<Event<Picked<Any>>>
        get() = _onItemClicked

    fun onItemClick(item: Picked<Any>) {
        _onItemClicked.value = Event(item)
    }
}
