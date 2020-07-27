package com.androgen.template.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androgen.template.data.repo.UserRepository
import com.androgen.template.model.EmptyEvent
import com.androgen.template.util.invoke
import com.androgen.template.view.BaseViewModel

class ContainerViewModel(userRepository: UserRepository) :
    BaseViewModel() {

    private val _onNavLogin = MutableLiveData<EmptyEvent>()
    val onNavLogin: LiveData<EmptyEvent> get() = _onNavLogin

    private val _onNavHome = MutableLiveData<EmptyEvent>()
    val onNavHome: LiveData<EmptyEvent> get() = _onNavHome

    init {
        when {

            userRepository.isLoggedIn() -> _onNavHome()
            else -> _onNavLogin()

        }
    }

}