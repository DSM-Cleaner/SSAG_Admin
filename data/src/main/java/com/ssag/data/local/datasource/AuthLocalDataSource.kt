package com.ssag.data.local.datasource

import com.ssag.domain.feature.auth.parameter.LoginParameter

interface AuthLocalDataSource {

    fun saveToken(token: String)

    fun isTokenEmpty(): Boolean

    fun clearLocalData()

    fun saveTeacher(loginParameter: LoginParameter)

    fun fetchTeacher(): LoginParameter
}