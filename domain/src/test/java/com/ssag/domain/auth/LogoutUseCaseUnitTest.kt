package com.ssag.domain.auth

import com.nhaarman.mockitokotlin2.mock
import com.ssag.domain.auth.repository.AuthRepository
import com.ssag.domain.auth.usecase.LogoutUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class LogoutUseCaseUnitTest {

    private val authRepository = mock<AuthRepository>()

    private val logoutUseCase = LogoutUseCase(authRepository)

    @Test
    fun testLogoutUseCase() {
        runBlocking {
            val logoutResult = logoutUseCase.execute(Unit)
            assertEquals(logoutResult, Unit)
        }
    }
}