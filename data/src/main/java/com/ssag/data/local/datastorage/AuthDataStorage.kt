package com.ssag.data.local.datastorage

import com.ssag.domain.feature.auth.parameter.LoginParameter

interface AuthDataStorage {

    fun setAccessToken(token: String)

    fun fetchAccessToken(): String

    fun clearDataStorage()

    fun saveTeacher(loginParameter: LoginParameter)

    fun fetchTeacher(): LoginParameter
}