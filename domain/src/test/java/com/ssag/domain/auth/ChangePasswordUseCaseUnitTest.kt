package com.ssag.domain.auth

import com.nhaarman.mockitokotlin2.mock
import com.ssag.domain.auth.parameter.ChangePasswordParameter
import com.ssag.domain.auth.repository.AuthRepository
import com.ssag.domain.auth.usecase.ChangePasswordUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ChangePasswordUseCaseUnitTest {

    private val authRepository = mock<AuthRepository>()

    private val changePasswordUseCase = ChangePasswordUseCase(authRepository)

    @Test
    fun testChangePasswordUseCase() {
        val currentPassword = "currentPassword"
        val newPassword = "newPassword"

        runBlocking {
            val result =
                changePasswordUseCase.execute(ChangePasswordParameter(currentPassword, newPassword))
            assertEquals(result, Unit)
        }
    }
}