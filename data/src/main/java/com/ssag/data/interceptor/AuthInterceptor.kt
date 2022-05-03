package com.ssag.data.interceptor

import com.ssag.data.local.datastorage.AuthDataStorage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authDataStorage: AuthDataStorage
) : Interceptor {

    private val notNeedTokenApiPaths = listOf(
        "teacher/login"
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url().encodedPath()
        if(notNeedTokenApiPaths.contains(path)) return chain.proceed(request)

        val accessToken = authDataStorage.fetchAccessToken()
        return chain.proceed(
            request.newBuilder().addHeader(
                "Authorization",
                "Bearer $accessToken"
            ).build()
        )
    }
}