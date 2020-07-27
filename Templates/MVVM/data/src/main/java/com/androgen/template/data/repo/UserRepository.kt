package com.androgen.template.data.repo

import com.androgen.template.data.model.UserModel
import io.reactivex.Single

interface UserRepository {

    fun isLoggedIn(): Boolean

    fun getProfile(): Single<UserModel>

}