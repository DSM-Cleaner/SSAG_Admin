package com.ssag.data.local.datastorage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthDataStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AuthDataStorage {

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
    }

    override fun setAccessToken(token: String) {
        runBlocking(Dispatchers.IO) {
            context.dataStore.edit {
                it[ACCESS_TOKEN_KEY] = token
            }
        }
    }

    override fun fetchAccessToken(): String {
        val token = runBlocking(Dispatchers.IO) {
            return@runBlocking context.dataStore.data.map {
                it[ACCESS_TOKEN_KEY]
            }.firstOrNull()
        }
        return "Bearer $token"
    }

    override fun clearDataStorage() {
        runBlocking(Dispatchers.IO) {
            context.dataStore.edit {
                it.clear()
            }
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("ssag_auth")
