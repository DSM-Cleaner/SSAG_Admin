package com.ssag.data.local.datastorage

import com.ssag.domain.feature.auth.parameter.LoginParameter

interface AuthDataStorage {

    fun setAccessToken(token: String)

    fun fetchAccessToken(): String

    fun clearDataStorage()

    fun saveTeacher(loginParameter: LoginParameter)

    fun saveTeacherId(id: Long)

    fun fetchTeacher(): LoginParameter

    fun fetchTeacherId(): Long
}