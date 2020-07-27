package com.androgen.template.data.model.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiEmptyResponse(

    @Expose
    @SerializedName("status")
    val status: Int

)