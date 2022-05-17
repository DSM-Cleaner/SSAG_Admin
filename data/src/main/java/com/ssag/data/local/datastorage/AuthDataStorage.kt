package com.ssag.data.local.datastorage

interface AuthDataStorage {

    fun setAccessToken(token: String)

    fun fetchAccessToken(): String

    fun clearDataStorage()
}