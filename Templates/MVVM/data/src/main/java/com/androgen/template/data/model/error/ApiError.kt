package com.androgen.template.data.model.error

class ApiError(private val statusCode: Int) : Exception() {

    val status: ApiStatusType = when (statusCode) {

        0 -> ApiStatusType.NoError
        1 -> ApiStatusType.UnknownError
        2 -> ApiStatusType.MalformedRequest

        else -> ApiStatusType.UnknownError

    }

    override val message: String?
        get() = "API Error (Code = $statusCode, Value = $status)"

}