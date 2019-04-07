package com.katsiro.alexey.gia.utils.extensions

import androidx.lifecycle.*
import com.katsiro.alexey.gia.utils.Event
import com.katsiro.alexey.gia.utils.EventObserver

inline fun <T> LiveData<T>.observeK(owner: LifecycleOwner, crossinline observer: (T?) -> Unit) {
    this.observe(owner, Observer { observer(it) })
}

inline fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    this.observe(owner, Observer { it?.run(observer) })
}

inline fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    observe(owner, EventObserver { observer(it) })
}

inline fun <T> LiveData<T>.observeValue(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    observe(owner, Observer { observer(it) })
}

/** Uses `Transformations.map` on a LiveData */
fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}

fun <X, Y> LiveData<X>.mapToEvent(body: (X) -> Y): LiveData<Event<Y>> {
    return Transformations.map(this) {
        Event(body(it))
    }
}

/** Uses `Transformations.switchMap` on a LiveData */
fun <X, Y> LiveData<X>.switchMap(body: (X) -> LiveData<Y>): LiveData<Y> {
    return Transformations.switchMap(this, body)
}

fun <T> MutableLiveData<T>.setValueIfNew(newValue: T) {
    if (this.value != newValue) value = newValue
}

fun <T> MutableLiveData<T>.postValueIfNew(newValue: T) {
    if (this.value != newValue) postValue(newValue)
}

fun <T> MutableLiveData<Event<T>>.postEventValue(value: T) {
    postValue(Event(value))
}

fun <T> MutableLiveData<Event<T>>.setEventValue(value: T) {
    setValue(Event(value))
}

fun MutableLiveData<Unit>.trigger() {
    value = Unit
}
