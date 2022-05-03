package com.ssag.data.local.datastorage

import kotlinx.coroutines.*

class AuthDataStorageImpl : AuthDataStorage {

    override fun setAccessToken(token: String) {
        CoroutineScope(Dispatchers.IO).launch {

        }
    }

    override fun fetchAccessToken(): String {
        val a = runBlocking(Dispatchers.IO) {
            return@runBlocking ""
        }
        return a
    }
}
