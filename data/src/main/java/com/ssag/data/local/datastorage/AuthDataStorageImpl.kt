package com.ssag.data.local.datastorage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ssag.domain.feature.auth.parameter.LoginParameter
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthDataStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AuthDataStorage {

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val TEACHER_NAME_KEY = stringPreferencesKey("teacher_name")
        private val TEACHER_PASSWORD_KEY = stringPreferencesKey("teacher_pass")
        private val TEACHER_ID_KEY = longPreferencesKey("teacher_id")
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
        return token ?: ""
    }

    override fun clearDataStorage() {
        runBlocking(Dispatchers.IO) {
            context.dataStore.edit {
                it.clear()
            }
        }
    }

    override fun saveTeacher(loginParameter: LoginParameter) {
        runBlocking(Dispatchers.IO) {
            context.dataStore.edit {
                it[TEACHER_NAME_KEY] = loginParameter.name
                it[TEACHER_PASSWORD_KEY] = loginParameter.password
            }
        }
    }

    override fun saveTeacherId(id: Long) {
        runBlocking(Dispatchers.IO) {
            context.dataStore.edit {
                it[TEACHER_ID_KEY] = id
            }
        }
    }

    override fun fetchTeacher(): LoginParameter {
        val teacher = runBlocking(Dispatchers.IO) {
            context.dataStore.data.map {
                LoginParameter(it[TEACHER_NAME_KEY] ?: "", it[TEACHER_PASSWORD_KEY] ?: "")
            }.first()
        }

        return teacher
    }

    override fun fetchTeacherId(): Long {
        val teacherId = runBlocking(Dispatchers.IO) {
            context.dataStore.data.map {
                it[TEACHER_ID_KEY] ?: 0L
            }.first()
        }

        return teacherId
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("ssag_auth")
