package com.androgen.template.data.model.dto.response

import com.androgen.template.data.model.UserModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserDTO(

    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("name")
    val name: String

) {

    @Throws(Exception::class)
    fun toModel(): UserModel = UserModel(this.id, this.name)

}