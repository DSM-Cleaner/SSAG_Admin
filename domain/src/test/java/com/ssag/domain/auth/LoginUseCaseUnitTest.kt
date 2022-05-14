package com.ssag.domain.auth

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.ssag.domain.auth.entity.TeacherEntity
import com.ssag.domain.auth.repository.AuthRepository
import com.ssag.domain.auth.usecase.LoginUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginUseCaseUnitTest {

    private val authRepository = mock<AuthRepository>()

    private val loginUseCase = LoginUseCase(authRepository)

    @Test
    fun testLoginUseCase() {
        val teacherEntity = TeacherEntity(13L, "조건웅", true)

        val password = "password"

        runBlocking {
            whenever(authRepository.login(password)).thenReturn(
                teacherEntity
            )

            val result = loginUseCase.execute(password)
            assertEquals(result, teacherEntity)
        }
    }
}