package com.ssag.data.local.datasource

import com.ssag.data.local.datastorage.AuthDataStorage
import com.ssag.domain.feature.auth.parameter.LoginParameter
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val authDataStorage: AuthDataStorage
) : AuthLocalDataSource {

    override fun saveToken(token: String) {
        authDataStorage.setAccessToken(token)
    }

    override fun isTokenEmpty(): Boolean =
        authDataStorage.fetchAccessToken().isEmpty()

    override fun clearLocalData() {
        authDataStorage.clearDataStorage()
    }

    override fun saveTeacher(loginParameter: LoginParameter) {
        authDataStorage.saveTeacher(loginParameter)
    }

    override fun saveTeacherId(id: Long) {
        authDataStorage.saveTeacherId(id)
    }

    override fun fetchTeacherId(): Long =
        authDataStorage.fetchTeacherId()


    override fun fetchTeacher(): LoginParameter =
        authDataStorage.fetchTeacher()
}