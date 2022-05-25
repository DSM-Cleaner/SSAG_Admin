package com.ssag.data.local.datasource

import com.ssag.domain.feature.auth.parameter.LoginParameter

interface AuthLocalDataSource {

    fun saveToken(token: String)

    fun isTokenEmpty(): Boolean

    fun clearLocalData()

    fun saveTeacher(loginParameter: LoginParameter)

    fun saveTeacherId(id: Long)

    fun fetchTeacher(): LoginParameter

    fun fetchTeacherId(): Long
}