package com.androgen.template.data.repo.impl

import com.androgen.template.data.model.UserModel
import com.androgen.template.data.repo.UserRepository
import com.androgen.template.data.rest.UserApi
import com.androgen.template.data.rest.apify
import com.androgen.template.data.store.TokenStore
import io.reactivex.Single

class UserRepositoryImpl(private val api: UserApi, private val tokenStore: TokenStore) :
    UserRepository {

    override fun isLoggedIn(): Boolean = tokenStore.get() != null

    override fun getProfile(): Single<UserModel> =
        api.getProfile().apify().map { it.toModel() }

}