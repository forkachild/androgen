package com.androgen.template.data.rest

import com.androgen.template.data.model.dto.response.ApiResponse
import com.androgen.template.data.model.dto.response.UserDTO
import io.reactivex.Single
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    fun getProfile(): Single<ApiResponse<UserDTO>>

}