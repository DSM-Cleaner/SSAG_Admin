package com.ssag.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") val token: String,
    @SerializedName("id") val id: Long,
    @SerializedName("gender") val gender: Boolean
)