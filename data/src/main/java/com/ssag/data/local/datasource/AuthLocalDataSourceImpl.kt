package com.ssag.data.local.datasource

import com.ssag.data.local.datastorage.AuthDataStorage
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val authDataStorage: AuthDataStorage
): AuthLocalDataSource {

    override fun clearLocalData() {
        authDataStorage.clearDataStorage()
    }
}