package com.ssag.data.remote.request

import com.google.gson.annotations.SerializedName
import com.ssag.domain.auth.parameter.LoginParameter

data class LoginRequest(
    @SerializedName("name") val name: String,
    @SerializedName("password") val password: String
)

fun LoginParameter.toRequest() =
    LoginRequest(
        name = name,
        password = password
    )