package com.androgen.template.data.model.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(

    @Expose
    @SerializedName("status")
    val status: Int,

    @Expose
    @SerializedName("data")
    val data: T

)