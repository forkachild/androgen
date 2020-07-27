package com.androgen.template.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androgen.template.model.EmptyEvent
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val _navBack = MutableLiveData<EmptyEvent>()
    val navBack: LiveData<EmptyEvent> get() = _navBack

    protected val disp = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disp.clear()
    }

}