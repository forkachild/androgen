package com.androgen.template.data.model.dto.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginUserDTO(

    @Expose
    @SerializedName("countryCode")
    val countryCode: Int,

    @Expose
    @SerializedName("phoneNumber")
    val phoneNumber: String

)