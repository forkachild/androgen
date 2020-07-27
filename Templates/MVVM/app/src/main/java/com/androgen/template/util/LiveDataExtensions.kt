package com.androgen.template.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.androgen.template.model.EmptyEvent
import com.androgen.template.model.Event

operator fun MutableLiveData<EmptyEvent>.invoke() {
    this.value = EmptyEvent()
}

operator fun <T> MutableLiveData<Event<T>>.invoke(value: T) {
    this.value = Event(value)
}

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, block: (T?) -> Unit) {
    this.observe(lifecycleOwner, Observer {
        block(it)
    })
}

fun <T> LiveData<T>.observeNonNull(lifecycleOwner: LifecycleOwner, block: (T) -> Unit) {
    this.observe(lifecycleOwner, Observer {
        if (it != null) {
            block(it)
        }
    })
}

fun <T> LiveData<Event<T>>.observeEvent(lifecycleOwner: LifecycleOwner, block: (T) -> Unit) {
    this.observe(lifecycleOwner, Observer {
        it.consume()?.let { value ->
            block(value)
        }
    })
}

fun LiveData<EmptyEvent>.observeEmptyEvent(lifecycleOwner: LifecycleOwner, block: () -> Unit) {
    this.observe(lifecycleOwner, Observer {
        if (it.consume()) {
            block()
        }
    })
}