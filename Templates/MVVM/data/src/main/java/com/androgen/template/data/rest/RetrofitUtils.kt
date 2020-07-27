package com.androgen.template.data.rest

import com.androgen.template.data.model.dto.response.ApiEmptyResponse
import com.androgen.template.data.model.dto.response.ApiResponse
import com.androgen.template.data.model.error.ApiError
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

inline fun <reified T> createRetrofitAPI(): T {
    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .callTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    return Retrofit.Builder()
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("url")
        .build()
        .create(T::class.java)
}

fun <T> Single<T>.iofy(): Single<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(Schedulers.io())

fun <T> Single<ApiResponse<T>>.apify(): Single<T> =
    this.iofy().map {
        if (it.status > 0) {
            throw ApiError(it.status)
        }

        it.data
    }

fun Single<ApiEmptyResponse>.apifyEmpty(): Completable =
    this.iofy().flatMapCompletable {
        if (it.status > 0) {
            throw ApiError(it.status)
        }

        Completable.complete()
    }