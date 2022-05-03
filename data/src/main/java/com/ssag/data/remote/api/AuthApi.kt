package com.ssag.data.remote.api

import com.ssag.data.remote.request.ChangePasswordRequest
import com.ssag.data.remote.request.LoginRequest
import com.ssag.data.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApi {

    @POST("teacher/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @PATCH("teacher/password")
    suspend fun changePassword(
        @Body request: ChangePasswordRequest
    )
}