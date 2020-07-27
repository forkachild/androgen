package com.androgen.template

import com.androgen.template.data.repo.UserRepository
import com.androgen.template.data.repo.impl.UserRepositoryImpl
import com.androgen.template.data.rest.Api
import com.androgen.template.data.rest.createRetrofitAPI
import com.androgen.template.data.store.TokenStore
import org.koin.dsl.module

val appModule = module {

    single { TokenStore(get()) }
    single { createRetrofitAPI<Api>() }
    single<UserRepository> { UserRepositoryImpl(get<Api>(), get()) }



}