package com.ssag.data.remote.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("password") val password: String
)