package com.ssag.data.local.datastorage

interface AuthDataStorage {

    suspend fun setAccessToken(token: String)

    fun fetchAccessToken(): String


}